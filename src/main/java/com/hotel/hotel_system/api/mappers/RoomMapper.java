package com.hotel.hotel_system.api.mappers;

import com.hotel.hotel_system.api.dto.requests.RoomCreateDto;
import com.hotel.hotel_system.api.dto.requests.RoomUpdateDto;
import com.hotel.hotel_system.api.dto.responses.RoomResponseDto;
import com.hotel.hotel_system.store.entities.RoomEntity;

public interface RoomMapper {
    RoomEntity toEntity(RoomCreateDto dto);
    RoomResponseDto toResponseDto(RoomEntity entity);
    RoomEntity updateRoomEntity(RoomUpdateDto dto, RoomEntity entity);
}