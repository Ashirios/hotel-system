package com.hotel.hotel_system.api.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;



import org.springframework.stereotype.Service;

import com.hotel.hotel_system.api.dto.requests.RoomCreateDto;
import com.hotel.hotel_system.api.dto.requests.RoomUpdateDto;
import com.hotel.hotel_system.api.dto.responses.RoomResponseDto;
import com.hotel.hotel_system.api.mappers.RoomMapper;
import com.hotel.hotel_system.store.entities.RoomEntity;
import com.hotel.hotel_system.store.enums.RoomType;
import com.hotel.hotel_system.store.repositories.RoomRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class RoomService {

    private final RoomRepository repository;
    private final RoomMapper mapper;

    public  RoomService (RoomRepository repository, RoomMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
        
    }

    public RoomResponseDto addRoom(RoomCreateDto dto){
        RoomEntity entity = mapper.toEntity(dto);
        repository.findByRoomNumber(entity.getRoomNumber())
            .ifPresent(r ->{
                throw new RuntimeException("Room already exists");
            }); 
        RoomEntity savedEntity = repository.save(entity);
        RoomResponseDto response = mapper.toResponseDto(savedEntity);
        return response;
        
    }


    
    public void deleteRoom(Long id){
        
        RoomEntity entity = repository.findById(id)
            .orElseThrow(() -> 
               new RuntimeException()
            );
        
        repository.delete(entity);
    }

   
    public RoomResponseDto updateRoom(RoomUpdateDto dto, Long id){
        RoomEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        RoomEntity updatedEntity = mapper.updateRoomEntity(dto, entity);
        repository.save(updatedEntity);
        return mapper.toResponseDto(updatedEntity);
    }


    public List<RoomResponseDto> availableBetweenPrice(BigDecimal minPrice, BigDecimal maxPrice){
        List<RoomEntity> entities = repository.findByPriceBetween(minPrice, maxPrice);
        List<RoomResponseDto> responses = entities.stream().map(mapper::toResponseDto).collect(Collectors.toList());

        return responses;
    }

    public List<RoomResponseDto> getAllRoomByType(RoomType type){

        List<RoomEntity> entities = repository.findAllByRoomType(type);
        List<RoomResponseDto> responses = entities.stream().map(mapper::toResponseDto).collect(Collectors.toList());
        return responses;

    }

    public RoomResponseDto getRoomById(Long id){

        RoomResponseDto dto = repository.findById(id)
                                    .map(mapper::toResponseDto)
                                    .orElseThrow(() -> new EntityNotFoundException(""));
        return dto;
    }

    public List<RoomResponseDto> getAllRoom(){

         List<RoomEntity> entities = repository.findAll();
        List<RoomResponseDto> responses = entities.stream().map(mapper::toResponseDto).collect(Collectors.toList());
        return responses;

    }

}
