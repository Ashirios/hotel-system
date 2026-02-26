package com.hotel.hotel_system.api.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.hotel.hotel_system.api.dto.requests.LoginRequestDto;
import com.hotel.hotel_system.api.dto.requests.RegistrationDto;
import com.hotel.hotel_system.api.dto.requests.UserUpdateDto;
import com.hotel.hotel_system.api.dto.responses.UserProfileDto;
import com.hotel.hotel_system.store.entities.UserEntity;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", source = "password")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", expression = "java(com.hotel.hotel_system.store.enums.Role.USER)")
    @Mapping(target = "bookings", ignore = true)
    UserEntity toEntity(RegistrationDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "password")
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    UserEntity updateUserEntity(UserUpdateDto dto, @MappingTarget UserEntity entity);

    UserProfileDto toProfileDto(UserEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "password")
    @Mapping(target = "username", source  = "username")
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "email", ignore = true)
    UserEntity toEntityLogin(LoginRequestDto dto);

    


}
