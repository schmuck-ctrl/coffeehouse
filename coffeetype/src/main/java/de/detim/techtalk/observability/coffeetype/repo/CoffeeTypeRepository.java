package de.detim.techtalk.observability.coffeetype.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CoffeeTypeRepository extends JpaRepository<CoffeeType, UUID> {

    Optional<CoffeeType> findByName(String name);
}
