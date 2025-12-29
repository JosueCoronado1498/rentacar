package com.jcoronado.rentacar.users.repository;

import com.jcoronado.rentacar.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
