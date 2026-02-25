package com.hotel.hotel_system.api.dto.responses;

import java.math.BigDecimal;

import com.hotel.hotel_system.store.enums.RoomType;

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
public class RoomResponseDto {
    
    Long id;
    RoomType roomType;
    int roomNumber;
    BigDecimal price;

}
