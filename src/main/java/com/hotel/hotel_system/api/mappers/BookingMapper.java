package com.hotel.hotel_system.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.hotel.hotel_system.api.dto.requests.BookingCreateDto;
import com.hotel.hotel_system.api.dto.responses.BookingResponseDto;
import com.hotel.hotel_system.store.entities.BookingEntity;

@Mapper(componentModel = "spring", imports = {java.time.LocalDateTime.class})
public interface BookingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "totalPrice", ignore = true)
    BookingEntity toEntity(BookingCreateDto dto);

    @Mapping(target = "userId", source = "user.id")           
    @Mapping(target = "username", source = "user.username")   
    @Mapping(target = "roomId", source = "room.id")           
    @Mapping(target = "roomNumber", source = "room.roomNumber")
    BookingResponseDto toResponseDto(BookingEntity entity);
}