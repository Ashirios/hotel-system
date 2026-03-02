package com.hotel.hotel_system.api.services;



import org.springframework.stereotype.Service;

import com.hotel.hotel_system.api.dto.requests.LoginRequestDto;
import com.hotel.hotel_system.api.dto.requests.RegistrationDto;
import com.hotel.hotel_system.api.dto.requests.UserUpdateDto;
import com.hotel.hotel_system.api.dto.responses.UserProfileDto;
import com.hotel.hotel_system.api.mappers.UserMapper;
import com.hotel.hotel_system.store.entities.UserEntity;
import com.hotel.hotel_system.store.repositories.UserRepository;
import com.hotel.hotel_system.api.exceptions.EntityNotFoundException;


@Service
public class UserService {

    private final UserMapper mapper;
    private final UserRepository repository;


    public UserService(UserMapper mapper, UserRepository repository){
        this.mapper = mapper;
        this.repository = repository;
    }

    public UserProfileDto addUser(RegistrationDto dto){

        UserEntity entity = mapper.toEntity(dto);
        repository.findByEmail(entity.getEmail())
            .ifPresent(r ->{
                throw new RuntimeException("User is already exist!");
            });

        //encode password 

        

        UserEntity savedEntity = repository.save(entity);
        UserProfileDto profile = mapper.toProfileDto(savedEntity);
        
        return profile;
        
    } 

    public UserProfileDto updateUser(UserUpdateDto dto, Long id){

        UserEntity entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User doesn't exist"));
        UserEntity updatedEntity = mapper.updateUserEntity(dto, entity);
        UserEntity savedEntity = repository.save(updatedEntity);
        UserProfileDto profile = mapper.toProfileDto(savedEntity);
        return profile;


        
    }

    public void deleteUser(Long id){

        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User doesn't exist"));
        repository.deleteById(id);

    }

    //remake login to optimize proccess
    public UserProfileDto login(LoginRequestDto dto){
    //encode password

        UserEntity entity = mapper.toEntityLogin(dto);
        UserEntity entity2 = repository.findByPassword(entity.getPassword()).orElseThrow(() -> new EntityNotFoundException("User doesn't exist"));
        if (entity.getUsername().equals(entity2.getUsername())) {
            UserProfileDto dto2 = mapper.toProfileDto(entity2);
            return dto2;
        }
        throw new RuntimeException("User doesnt exist");

    }


    public UserProfileDto getById(Long id){
        UserEntity entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User doesn't exist"));

        return mapper.toProfileDto(entity);

    }



    

}
