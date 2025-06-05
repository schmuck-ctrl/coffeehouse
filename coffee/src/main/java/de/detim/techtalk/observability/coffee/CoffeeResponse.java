package de.detim.techtalk.observability.coffee;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor
public class CoffeeResponse {
    private final CoffeeType type;
    private final CoffeeSize size;
}
