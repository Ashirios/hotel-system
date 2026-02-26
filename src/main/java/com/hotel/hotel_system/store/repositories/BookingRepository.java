package com.hotel.hotel_system.store.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotel.hotel_system.store.entities.BookingEntity;
import com.hotel.hotel_system.store.enums.Status;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long>{


    @Query("SELECT b.user FROM bookings b WHERE b.user.id = :userId ")
    List<BookingEntity> findUserByBookingId(@Param("userId") Long userId);

    @Query("SELECT b.room FROM bookings b WHERE b.room.id = :roomId ")
    List<BookingEntity> findRoomByBookingId(@Param("roomId") Long roomId);

    List<BookingEntity> findByStatus(Status status);

    boolean existsByRoomIdAndStatusNotAndEndDateAfterAndStartDateBefore(Long roomId, Status status, LocalDate startDate, LocalDate endDate );

    List<BookingEntity> findByUsername(String username);

}
