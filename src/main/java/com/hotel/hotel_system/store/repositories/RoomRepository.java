package com.hotel.hotel_system.store.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.hotel_system.store.entities.RoomEntity;
import com.hotel.hotel_system.store.enums.RoomType;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long>{

    List<RoomEntity> findAllByRoomType(RoomType roomType);

    List<RoomEntity> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);


    Optional<RoomEntity> findByRoomNumber(int roomNumber);

    



}
