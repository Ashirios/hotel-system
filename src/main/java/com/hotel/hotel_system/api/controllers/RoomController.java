package com.hotel.hotel_system.api.controllers;

import com.hotel.hotel_system.api.dto.requests.RoomCreateDto;
import com.hotel.hotel_system.api.dto.requests.RoomUpdateDto;
import com.hotel.hotel_system.api.dto.responses.RoomResponseDto;
import com.hotel.hotel_system.api.services.RoomService;
import com.hotel.hotel_system.store.enums.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private RoomService service;

    @Autowired
    public RoomController(RoomService service){
        this.service = service;

    }
    @PostMapping("/create")
    public RoomResponseDto addRoom(@RequestBody RoomCreateDto dto){
        return service.addRoom(dto);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteRoom(id);
    }


    @PostMapping("/update/{id}")
    public RoomResponseDto update(@RequestBody RoomUpdateDto dto, @PathVariable Long id){
        return service.updateRoom(dto, id);
    }


    @GetMapping("/{id}")
    public RoomResponseDto getById(@PathVariable Long id){
        return service.getRoomById(id);
    }

    @GetMapping("")
    public List<RoomResponseDto> getAllRoom(){
        return  service.getAllRoom();
    }

    @GetMapping("/type/{type}")
    public List<RoomResponseDto> getAllByType(@PathVariable RoomType type){
        return service.getAllRoomByType(type);
    }

    @GetMapping("/available")
    public List<RoomResponseDto> availableByPrice(@RequestParam("minPrice") BigDecimal minPrice, @RequestParam("maxPrice") BigDecimal maxPrice){
        return service.availableBetweenPrice(minPrice, maxPrice);
    }



}
