package de.detim.techtalk.observability.coffee.service;

import de.detim.techtalk.observability.coffee.CoffeeResponse;
import de.detim.techtalk.observability.coffee.CoffeeSize;
import de.detim.techtalk.observability.coffee.CoffeeType;

import java.util.Collection;

public interface CoffeeService {

    CoffeeResponse make(String name, String size);

    Collection<CoffeeType> coffeeTypes();

    Collection<CoffeeSize> coffeeSizes();
}
