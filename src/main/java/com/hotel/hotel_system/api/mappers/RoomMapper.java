package com.hotel.hotel_system.api.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.hotel.hotel_system.api.dto.requests.RoomCreateDto;
import com.hotel.hotel_system.api.dto.requests.RoomUpdateDto;
import com.hotel.hotel_system.api.dto.responses.RoomResponseDto;
import com.hotel.hotel_system.store.entities.RoomEntity;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(target = "id", ignore = true)
    RoomEntity toEntity(RoomCreateDto dto);
    
    
    RoomResponseDto toResponseDto(RoomEntity entity);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roomNumber", ignore = true)
    RoomEntity updateRoomEntity(RoomUpdateDto dto, @MappingTarget RoomEntity entity);
    
}
