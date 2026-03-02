package com.hotel.hotel_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@ComponentScan(basePackages = {"com.hotel.hotel_system.api.controllers", 
                               "com.hotel.hotel_system.api.services",
                               "com.hotel.hotel_system.store.repositories",
								"com.hotel.hotel_system.api.mappers",
								"com.hotel.hotel_system.api.dto",
								"com.hotel.hotel_system.api.exceptions",
								"com.hotel.hotel_system.store.entities",
								"com.hotel.hotel_system.store.enums"})
public class HotelSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelSystemApplication.class, args);
	}

}
