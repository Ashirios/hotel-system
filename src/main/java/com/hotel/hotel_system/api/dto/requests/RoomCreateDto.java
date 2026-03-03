package com.hotel.hotel_system.api.dto.requests;

import java.math.BigDecimal;

import com.hotel.hotel_system.store.enums.RoomType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("room_type")
    RoomType roomType;
    @JsonProperty("room_number")
    int roomNumber;
    BigDecimal price;


}
