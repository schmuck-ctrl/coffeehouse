package de.detim.techtalk.observability.coffeesize.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CoffeeSizeRepository extends JpaRepository<CoffeeSize, UUID> {

    Optional<CoffeeSize> findByName(String name);
}
