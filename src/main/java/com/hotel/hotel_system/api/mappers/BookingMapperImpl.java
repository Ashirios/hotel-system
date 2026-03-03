package com.hotel.hotel_system.api.mappers;

import org.springframework.stereotype.Component;
import com.hotel.hotel_system.api.dto.requests.BookingCreateDto;
import com.hotel.hotel_system.api.dto.responses.BookingResponseDto;
import com.hotel.hotel_system.store.entities.BookingEntity;
import com.hotel.hotel_system.store.enums.Status;
import java.time.LocalDateTime;

@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public BookingEntity toEntity(BookingCreateDto dto) {
        if (dto == null) {
            return null;
        }

        BookingEntity entity = new BookingEntity();
        
        
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        
        
        entity.setStatus(Status.PENDING);
        entity.setCreatedAt(LocalDateTime.now());
        
        
        return entity;
    }

    @Override
    public BookingResponseDto toResponseDto(BookingEntity entity) {
        if (entity == null) {
            return null;
        }

        BookingResponseDto dto = new BookingResponseDto();
        
        
        dto.setId(entity.getId());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setTotalPrice(entity.getTotalPrice());
        
        
        if (entity.getStatus() != null) {
            dto.setStatus(entity.getStatus().name());
        }
        
        
        if (entity.getUser() != null) {
            dto.setUserId(entity.getUser().getId());
            dto.setUsername(entity.getUser().getUsername());
        }
        
        
        if (entity.getRoom() != null) {
            dto.setRoomId(entity.getRoom().getId());
            dto.setRoomNumber(String.valueOf(entity.getRoom().getRoomNumber()));
        }
        
        return dto;
    }
}