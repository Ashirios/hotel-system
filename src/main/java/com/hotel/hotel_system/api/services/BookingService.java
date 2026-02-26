package com.hotel.hotel_system.api.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hotel.hotel_system.api.dto.requests.BookingCreateDto;
import com.hotel.hotel_system.api.dto.responses.BookingResponseDto;
import com.hotel.hotel_system.api.mappers.BookingMapper;
import com.hotel.hotel_system.store.entities.BookingEntity;
import com.hotel.hotel_system.store.enums.Status;
import com.hotel.hotel_system.store.repositories.BookingRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class BookingService {

    final private BookingMapper mapper;
    final private BookingRepository repository;


    public BookingService(BookingMapper mapper, BookingRepository repository ){
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<BookingResponseDto> getAllBookings(){
        List<BookingEntity> entities = repository.findAll();
        List<BookingResponseDto> responses = entities.stream().map(mapper::toResponseDto).collect(Collectors.toList());
        return responses;
    }

    //add status !
    public boolean checkIfCanBook(Long roomId, LocalDate startDate, LocalDate endDate){
        boolean booked = repository.existsByRoomIdAndStatusNotAndEndDateAfterAndStartDateBefore(roomId, Status.CANCELLED, startDate, endDate);
        if(booked) return false;
        else return true;
    }

    public BookingResponseDto addBooking(BookingCreateDto dto){

        BookingEntity entity = mapper.toEntity(dto);
        BookingEntity savedEntity = repository.save(entity);
        BookingResponseDto response = mapper.toResponseDto(savedEntity);

        return response;

    }

    public List<BookingResponseDto> getAllUserBookings(Long userId){

        List<BookingEntity> entities = repository.findUserByBookingId(userId);
        List<BookingResponseDto>  responses = entities.stream().map(mapper::toResponseDto).collect(Collectors.toList());
        return responses;



    }

    public List<BookingResponseDto> getAllRoomBookings(Long roomId){
        List<BookingEntity> entities = repository.findRoomByBookingId(roomId);
        List<BookingResponseDto>  responses = entities.stream().map(mapper::toResponseDto).collect(Collectors.toList());
        return responses;
    }

    public void deleteBooking(Long id){
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking doesnt exist"));
        repository.deleteById(id);
    } 

    public BookingResponseDto getBookingById(Long id){
        BookingEntity entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking doesnt exist"));
        BookingResponseDto dto = mapper.toResponseDto(entity);
        return dto;
    }

}
