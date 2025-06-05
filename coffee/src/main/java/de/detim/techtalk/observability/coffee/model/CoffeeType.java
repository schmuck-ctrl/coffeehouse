package de.detim.techtalk.observability.coffee.model;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
@RequiredArgsConstructor
public class CoffeeType {
    private final UUID id;
    private final String name;
}
