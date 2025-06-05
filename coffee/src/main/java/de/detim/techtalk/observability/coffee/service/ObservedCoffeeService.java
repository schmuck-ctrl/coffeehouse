package de.detim.techtalk.observability.coffee.service;

import de.detim.techtalk.observability.coffee.CoffeeResponse;
import de.detim.techtalk.observability.coffee.CoffeeSize;
import de.detim.techtalk.observability.coffee.CoffeeType;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

@Slf4j
public class ObservedCoffeeService implements CoffeeService {

    private final CoffeeService delegate;
    private final ObservationRegistry registry;


    public ObservedCoffeeService(CoffeeService delegate, ObservationRegistry registry) {
        this.delegate = delegate;
        this.registry = registry;
    }

    @Override
    public CoffeeResponse make(String name, String size) {
        return Observation.createNotStarted("make.coffee", registry)
                .lowCardinalityKeyValue("name", name)
                .lowCardinalityKeyValue("size", size)
                .observe(() -> {
                    CoffeeResponse coffee = delegate.make(name, size);
                    log.info("Finished coffee: {}", coffee);
                    return coffee;
                });
        //durch observe brauchen wir start und stop und exception handling nicht, observe handelt das für uns
    }

    @Override
    @Observed(name = "coffee-service.load-all-coffee-types", contextualName = "coffee-service.find-all")
    public Collection<CoffeeType> coffeeTypes() {
        return delegate.coffeeTypes();
    }

    @Override
    public Collection<CoffeeSize> coffeeSizes() {
        return delegate.coffeeSizes();
    }
}
