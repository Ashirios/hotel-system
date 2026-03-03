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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserMapper mapper;
    private final UserRepository repository;

    public UserService(UserMapper mapper, UserRepository repository){
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<UserProfileDto> getAllUsers() {
        System.out.println("========== GET ALL USERS ==========");
        System.out.println("Calling repository.findAll()...");
        
        List<UserEntity> users = repository.findAll();
        
        System.out.println("Repository returned " + users.size() + " users");
        
        if (users.isEmpty()) {
            System.out.println("No users found in database!");
            return List.of(); // возвращаем пустой список
        }
        
        // Выводим каждого пользователя
        users.forEach(user -> {
            System.out.println("User from DB: id=" + user.getId() + 
                             ", username=" + user.getUsername() + 
                             ", email=" + user.getEmail() +
                             ", role=" + user.getRole());
        });
        
        System.out.println("Mapping users to DTOs...");
        
        List<UserProfileDto> dtos = users.stream()
            .map(user -> {
                System.out.println("Mapping user: " + user.getUsername());
                UserProfileDto dto = mapper.toProfileDto(user);
                System.out.println("Mapped to DTO: id=" + dto.getId() + 
                                 ", username=" + dto.getUsername() + 
                                 ", email=" + dto.getEmail());
                return dto;
            })
            .collect(Collectors.toList());
        
        System.out.println("Returning " + dtos.size() + " DTOs");
        System.out.println("===================================");
        
        return dtos;
    }

    public UserProfileDto addUser(RegistrationDto dto){
        UserEntity entity = mapper.toEntity(dto);
        repository.findByEmail(entity.getEmail())
            .ifPresent(r ->{
                throw new RuntimeException("User is already exist!");
            });
        UserEntity savedEntity = repository.save(entity);
        UserProfileDto profile = mapper.toProfileDto(savedEntity);
        return profile;
    } 

    public UserProfileDto updateUser(UserUpdateDto dto, Long id){
        UserEntity entity = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User doesn't exist"));
        UserEntity updatedEntity = mapper.updateUserEntity(dto, entity);
        UserEntity savedEntity = repository.save(updatedEntity);
        UserProfileDto profile = mapper.toProfileDto(savedEntity);
        return profile;
    }

    public void deleteUser(Long id){
        repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User doesn't exist"));
        repository.deleteById(id);
    }

    public UserProfileDto login(LoginRequestDto dto){
        UserEntity entity = mapper.toEntityLogin(dto);
        UserEntity entity2 = repository.findByPassword(entity.getPassword())
            .orElseThrow(() -> new EntityNotFoundException("User doesn't exist"));
        if (entity.getUsername().equals(entity2.getUsername())) {
            UserProfileDto dto2 = mapper.toProfileDto(entity2);
            return dto2;
        }
        throw new RuntimeException("User doesnt exist");
    }

    public UserProfileDto getById(Long id){
        UserEntity entity = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User doesn't exist"));
        return mapper.toProfileDto(entity);
    }
}