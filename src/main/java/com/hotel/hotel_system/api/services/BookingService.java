package com.hotel.hotel_system.api.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hotel.hotel_system.api.dto.requests.BookingCreateDto;
import com.hotel.hotel_system.api.dto.responses.BookingResponseDto;
import com.hotel.hotel_system.api.mappers.BookingMapper;
import com.hotel.hotel_system.store.entities.BookingEntity;
import com.hotel.hotel_system.store.entities.RoomEntity;
import com.hotel.hotel_system.store.entities.UserEntity;
import com.hotel.hotel_system.store.enums.Status;
import com.hotel.hotel_system.store.repositories.BookingRepository;
import com.hotel.hotel_system.store.repositories.RoomRepository;
import com.hotel.hotel_system.store.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository repository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final BookingMapper mapper;

    public List<BookingResponseDto> getAllBookings() {
        List<BookingEntity> entities = repository.findAll();
        return entities.stream()
            .map(mapper::toResponseDto)
            .collect(Collectors.toList());
    }

    
    public boolean checkIfCanBook(Long roomId, LocalDate startDate, LocalDate endDate) {
        boolean booked = repository.existsConflictingBooking(
            roomId, 
            startDate, 
            endDate, 
            Status.CANCELLED  
        );
        return !booked;  
    }

    public BookingResponseDto addBooking(BookingCreateDto dto) {
        BookingEntity entity = mapper.toEntity(dto);

        
        if (!checkIfCanBook(dto.getRoomId(), entity.getStartDate(), entity.getEndDate())) {
            throw new RuntimeException("You can't book this room! It is already booked!");
        }
        
        UserEntity user = userRepository.findById(dto.getUserId())
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
        RoomEntity room = roomRepository.findById(dto.getRoomId())
            .orElseThrow(() -> new EntityNotFoundException("Room not found"));
        
        long days = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate());
        BigDecimal totalPrice = room.getPrice().multiply(BigDecimal.valueOf(days));
        
        entity.setUser(user);
        entity.setRoom(room);
        entity.setTotalPrice(totalPrice);
        entity.setStatus(Status.PENDING);  
        
        BookingEntity savedEntity = repository.save(entity);
        return mapper.toResponseDto(savedEntity);
    }

    
    public List<BookingResponseDto> getAllUserBookings(Long userId) {
        List<BookingEntity> entities = repository.findByUserId(userId);
        return entities.stream()
            .map(mapper::toResponseDto)
            .collect(Collectors.toList());
    }

    
    public List<BookingResponseDto> getAllRoomBookings(Long roomId) {
        List<BookingEntity> entities = repository.findByRoomId(roomId);
        return entities.stream()
            .map(mapper::toResponseDto)
            .collect(Collectors.toList());
    }

    public void deleteBooking(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Booking doesn't exist"));
        repository.deleteById(id);
    }

    public BookingResponseDto getBookingById(Long id) {
        BookingEntity entity = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Booking doesn't exist"));
        return mapper.toResponseDto(entity);
    }
}