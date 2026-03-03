package com.hotel.hotel_system.api.mappers;

import org.springframework.stereotype.Component;
import com.hotel.hotel_system.api.dto.requests.LoginRequestDto;
import com.hotel.hotel_system.api.dto.requests.RegistrationDto;
import com.hotel.hotel_system.api.dto.requests.UserUpdateDto;
import com.hotel.hotel_system.api.dto.responses.UserProfileDto;
import com.hotel.hotel_system.store.entities.UserEntity;
import com.hotel.hotel_system.store.enums.Role;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toEntity(RegistrationDto dto) {
        if (dto == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setRole(Role.USER);
        
        
        
        return entity;
    }

    @Override
    public UserEntity updateUserEntity(UserUpdateDto dto, UserEntity entity) {
        if (dto == null || entity == null) {
            return entity;
        }

        if (dto.getPassword() != null) {
            entity.setPassword(dto.getPassword());
        }
        
        if (dto.getUsername() != null) {
            entity.setUsername(dto.getUsername());
        }
        
        return entity;
    }

    @Override
    public UserProfileDto toProfileDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        UserProfileDto dto = new UserProfileDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        
        return dto;
    }

    @Override
    public UserEntity toEntityLogin(LoginRequestDto dto) {
        if (dto == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        
        
        return entity;
    }
}