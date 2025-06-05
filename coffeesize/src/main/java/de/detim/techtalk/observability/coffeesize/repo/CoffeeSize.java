package de.detim.techtalk.observability.coffeesize.repo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Entity
@Value
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CoffeeSize {

    @Id
    @GeneratedValue
    UUID id;

    @Column(unique = true, nullable = false)
    String name;

    @Column(unique = true, nullable = false)
    String shortDescription;
}
