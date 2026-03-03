package com.hotel.hotel_system.api.dto.requests;

import java.time.LocalDate;
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
public class BookingCreateDto {
    @JsonProperty("user_id")
    Long userId;
    @JsonProperty("room_id")
    Long roomId;
    @JsonProperty("start_date")
    LocalDate startDate; 
    @JsonProperty("end_date")
    LocalDate endDate;   
}