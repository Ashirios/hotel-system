package com.hotel.hotel_system.store.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotel.hotel_system.store.entities.BookingEntity;
import com.hotel.hotel_system.store.entities.UserEntity;
import com.hotel.hotel_system.store.enums.Status;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    
    @Query("SELECT b FROM BookingEntity b WHERE b.user.id = :userId")
    List<BookingEntity> findByUserId(@Param("userId") Long userId);

   
    @Query("SELECT b FROM BookingEntity b WHERE b.room.id = :roomId")
    List<BookingEntity> findByRoomId(@Param("roomId") Long roomId);

   
    List<BookingEntity> findByStatus(Status status);

   
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BookingEntity b " +
           "WHERE b.room.id = :roomId AND b.status != :status " +
           "AND b.endDate > :startDate AND b.startDate < :endDate")
    boolean existsByRoomIdAndStatusNotAndOverlappingDates(
        @Param("roomId") Long roomId,
        @Param("status") Status status,
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate
    );

    
    @Query("SELECT b FROM BookingEntity b WHERE b.user.username = :username")
    List<BookingEntity> findByUserUsername(@Param("username") String username);
    
    
    @Query("SELECT b.user FROM BookingEntity b WHERE b.id = :bookingId")
    UserEntity findUserByBookingId(@Param("bookingId") Long bookingId);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BookingEntity b " +
           "WHERE b.room.id = :roomId AND b.status != :status " +
           "AND b.startDate < :endDate AND b.endDate > :startDate")
    boolean existsConflictingBooking(
        @Param("roomId") Long roomId,
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate,
        @Param("status") Status status
    );
}