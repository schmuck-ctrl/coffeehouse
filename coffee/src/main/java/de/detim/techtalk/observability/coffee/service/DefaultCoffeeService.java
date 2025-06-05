package de.detim.techtalk.observability.coffee.service;

import de.detim.techtalk.observability.coffee.model.CoffeeResponse;
import de.detim.techtalk.observability.coffee.model.CoffeeSize;
import de.detim.techtalk.observability.coffee.model.CoffeeType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.Collection;


public class DefaultCoffeeService implements CoffeeService { ;

    private final RestClient coffeeTypeClient;
    private final RestClient coffeeSizeClient;

    public DefaultCoffeeService(@Qualifier("coffeeTypeClient") RestClient coffeeTypeClient, @Qualifier("coffeeSizeClient")RestClient coffeeSizeClient) {
        this.coffeeTypeClient = coffeeTypeClient;
        this.coffeeSizeClient = coffeeSizeClient;
    }

    @Override
    public CoffeeResponse make(String name, String size) {

        CoffeeType coffeeType = coffeeTypeClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/coffee-types/search/findByName")
                        .queryParam("name", name)
                        .build())
                .retrieve()
                .toEntity(CoffeeType.class)
                .getBody();

        CoffeeSize coffeeSize = coffeeSizeClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/coffee-sizes/search/findByName")
                        .queryParam("name", size)
                        .build())
                .retrieve()
                .toEntity(CoffeeSize.class)
                .getBody();

        return CoffeeResponse.builder()
                .type(coffeeType)
                .size(coffeeSize)
                .build();
    }

    @Override
    public Collection<CoffeeType> coffeeTypes() {
        return coffeeTypeClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/coffee-types")
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<Collection<CoffeeType>>() {});
    }

    @Override
    public Collection<CoffeeSize> coffeeSizes() {
        return coffeeSizeClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/coffee-sizes")
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<Collection<CoffeeSize>>() {});
    }
}
