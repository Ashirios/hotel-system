package com.hotel.hotel_system.api.mappers;

import com.hotel.hotel_system.api.dto.requests.LoginRequestDto;
import com.hotel.hotel_system.api.dto.requests.RegistrationDto;
import com.hotel.hotel_system.api.dto.requests.UserUpdateDto;
import com.hotel.hotel_system.api.dto.responses.UserProfileDto;
import com.hotel.hotel_system.store.entities.UserEntity;

public interface UserMapper {
    UserEntity toEntity(RegistrationDto dto);
    UserEntity updateUserEntity(UserUpdateDto dto, UserEntity entity);
    UserProfileDto toProfileDto(UserEntity entity);
    UserEntity toEntityLogin(LoginRequestDto dto);
}