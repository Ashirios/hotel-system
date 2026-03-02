package com.hotel.hotel_system.api.dto.responses;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.Column;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponseDto {
    Long id;
    @JsonProperty("user_id")
    Long userId;
    String username;
    @JsonProperty("room_id")
    Long roomId;
    @JsonProperty("room_number")
    String roomNumber;
    @JsonProperty("created_at")
    LocalDateTime createdAt;
    String status;
    @JsonProperty("start_date")
    LocalDate startDate;
    @JsonProperty("end_date")
    LocalDate endDate;
    @JsonProperty("total_price")
    BigDecimal totalPrice;
}