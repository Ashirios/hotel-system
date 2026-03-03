package com.hotel.hotel_system.api.dto.requests;

import java.math.BigDecimal;
import com.hotel.hotel_system.store.enums.RoomType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomUpdateDto {
    @JsonProperty("room_type")
    RoomType roomType;
    //int roomNumber;
    BigDecimal price;

}
