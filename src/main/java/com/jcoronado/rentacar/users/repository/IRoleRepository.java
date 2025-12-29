package com.jcoronado.rentacar.users.repository;

import com.jcoronado.rentacar.users.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
