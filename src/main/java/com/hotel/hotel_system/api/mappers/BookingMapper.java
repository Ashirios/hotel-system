package com.hotel.hotel_system.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.hotel.hotel_system.api.dto.requests.BookingCreateDto;
import com.hotel.hotel_system.api.dto.responses.BookingResponseDto;
import com.hotel.hotel_system.store.entities.BookingEntity;
import com.hotel.hotel_system.store.entities.RoomEntity;
import com.hotel.hotel_system.store.entities.UserEntity;

@Mapper(componentModel = "spring", uses = {UserMapper.class, RoomMapper.class})
public interface BookingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "userId", qualifiedByName =  "userFromId" )
    @Mapping(target = "room", source = "roomId", qualifiedByName = "roomFromId")
    @Mapping(target = "status", expression = "java(com.hotel.hotel_system.store.enums.Status.PENDING)")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "totalPrice", ignore = true)
    BookingEntity toEntity(BookingCreateDto dto);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "roomId", source = "room.id")
    @Mapping(target = "roomNumber", source = "room.roomNumber")
    BookingResponseDto toResponseDto(BookingEntity entity);


    @Named("userFromId")
    default UserEntity userFromId(Long id){
        if(id == null) return null;
        UserEntity user = new UserEntity();
        user.setId(id);
        return user;
    }
    

    @Named("roomFromId")
    default RoomEntity roomFromId(Long id){
        if( id == null ) return null;
        RoomEntity room = new RoomEntity();
        room.setId(id);
        return room;
    }

}
