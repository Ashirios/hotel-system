package com.hotel.hotel_system.api.mappers;

import com.hotel.hotel_system.api.dto.requests.BookingCreateDto;
import com.hotel.hotel_system.api.dto.responses.BookingResponseDto;
import com.hotel.hotel_system.store.entities.BookingEntity;

public interface BookingMapper {
    BookingEntity toEntity(BookingCreateDto dto);
    BookingResponseDto toResponseDto(BookingEntity entity);
}