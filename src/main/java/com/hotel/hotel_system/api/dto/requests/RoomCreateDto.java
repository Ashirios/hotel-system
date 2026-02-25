package com.hotel.hotel_system.api.dto.requests;

import java.math.BigDecimal;

import com.hotel.hotel_system.store.enums.RoomType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomCreateDto {

    RoomType roomType;
    int roomNumber;
    BigDecimal price;


}
