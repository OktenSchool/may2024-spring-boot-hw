package org.example.may2024springhw.repositories;

import org.example.may2024springhw.enteties.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByEmail(String email);
    Optional<Owner> findByUsername(String username);
}
