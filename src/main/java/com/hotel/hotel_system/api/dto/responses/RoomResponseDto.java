package com.hotel.hotel_system.api.dto.responses;

import java.math.BigDecimal;

import com.hotel.hotel_system.store.enums.RoomType;
import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomResponseDto {
    
    Long id;
    @JsonProperty("room_type")
    RoomType roomType;
    @JsonProperty("room_number")
    int roomNumber;
    BigDecimal price;

}
