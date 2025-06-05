package de.detim.techtalk.observability.coffee;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
@RequiredArgsConstructor
public class CoffeeSize {
    private final UUID id;
    private final String name;
    private final String shortDescription;;
}
