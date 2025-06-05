package de.detim.techtalk.observability.coffee.service;

import de.detim.techtalk.observability.coffee.model.CoffeeResponse;
import de.detim.techtalk.observability.coffee.model.CoffeeSize;
import de.detim.techtalk.observability.coffee.model.CoffeeType;

import java.util.Collection;

public interface CoffeeService {

    CoffeeResponse make(String name, String size);

    Collection<CoffeeType> coffeeTypes();

    Collection<CoffeeSize> coffeeSizes();
}
