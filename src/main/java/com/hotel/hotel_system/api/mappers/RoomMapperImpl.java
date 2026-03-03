package com.hotel.hotel_system.api.mappers;

import org.springframework.stereotype.Component;
import com.hotel.hotel_system.api.dto.requests.RoomCreateDto;
import com.hotel.hotel_system.api.dto.requests.RoomUpdateDto;
import com.hotel.hotel_system.api.dto.responses.RoomResponseDto;
import com.hotel.hotel_system.store.entities.RoomEntity;

@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public RoomEntity toEntity(RoomCreateDto dto) {
        if (dto == null) {
            return null;
        }

        RoomEntity entity = new RoomEntity();
        entity.setRoomNumber(dto.getRoomNumber());
        entity.setRoomType(dto.getRoomType());
        entity.setPrice(dto.getPrice());
        
        return entity;
    }

    @Override
    public RoomResponseDto toResponseDto(RoomEntity entity) {
        if (entity == null) {
            return null;
        }

        RoomResponseDto dto = new RoomResponseDto();
        dto.setId(entity.getId());
        dto.setRoomNumber(entity.getRoomNumber());
        dto.setRoomType(entity.getRoomType());
        dto.setPrice(entity.getPrice());
        
        return dto;
    }

    @Override
    public RoomEntity updateRoomEntity(RoomUpdateDto dto, RoomEntity entity) {
        if (dto == null || entity == null) {
            return entity;
        }

        
        if (dto.getRoomType() != null) {
            entity.setRoomType(dto.getRoomType());
        }
        
        if (dto.getPrice() != null) {
            entity.setPrice(dto.getPrice());
        }
        return entity;
    }
}