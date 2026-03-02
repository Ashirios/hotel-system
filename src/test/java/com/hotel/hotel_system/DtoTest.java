package com.hotel.hotel_system;

import com.hotel.hotel_system.api.dto.requests.BookingCreateDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DtoTest {
    
    @Test
    public void testBookingCreateDto() {
        System.out.println("Starting test...");
        BookingCreateDto dto = new BookingCreateDto();
        assertNotNull(dto);
        System.out.println("✓ BookingCreateDto created successfully: " + dto.getClass().getName());
    }
}