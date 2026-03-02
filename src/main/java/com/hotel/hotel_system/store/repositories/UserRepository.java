package com.hotel.hotel_system.store.repositories;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.hotel_system.store.entities.UserEntity;
import com.hotel.hotel_system.store.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{


    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByPassword(String password);

    List<UserEntity> findByUsername(String username);

    List<UserEntity> findByUsernameContainingIgnoreCase(String name);

    List<UserEntity> findByRole(Role role);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);


}
