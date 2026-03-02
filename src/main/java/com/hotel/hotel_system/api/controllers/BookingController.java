package com.hotel.hotel_system.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.hotel.hotel_system.api.dto.requests.BookingCreateDto;
import com.hotel.hotel_system.api.dto.responses.BookingResponseDto;
import com.hotel.hotel_system.api.services.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private BookingService service;

    @Autowired
    public BookingController(BookingService service){
        this.service = service;
    }


    @PostMapping("/create")
    public BookingResponseDto create(@RequestBody BookingCreateDto dto){
        return service.addBooking(dto);
    }


    @GetMapping("")
    public List<BookingResponseDto> getAllBookings(){
        return service.getAllBookings();
    }


    @GetMapping("/user/{id}")
    public List<BookingResponseDto> getAllUserBookings(@PathVariable Long id){
        return service.getAllUserBookings(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteBooking(id);
    }

    @GetMapping("/room/{id}")
    public List<BookingResponseDto> getAllRoomBooking(@PathVariable Long id){
        return service.getAllRoomBookings(id);
    }

    @GetMapping("/{id}")
    public BookingResponseDto getBookingById(@PathVariable Long id){
        return service.getBookingById(id);
    }



    




}
