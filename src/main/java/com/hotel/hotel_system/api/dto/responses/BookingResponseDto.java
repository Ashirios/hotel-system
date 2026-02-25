package com.hotel.hotel_system.api.dto.responses;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponseDto {

    Long id;
    Long userId;
    String username;
    Long roomId;
    int roomNumber;
    LocalDate startDate;
    LocalDate endDate;
    BigDecimal totalPrice;

}
