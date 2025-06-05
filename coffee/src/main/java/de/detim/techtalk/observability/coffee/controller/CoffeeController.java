package de.detim.techtalk.observability.coffee.controller;

import de.detim.techtalk.observability.coffee.CoffeeResponse;
import de.detim.techtalk.observability.coffee.CoffeeSize;
import de.detim.techtalk.observability.coffee.CoffeeType;
import de.detim.techtalk.observability.coffee.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CoffeeController {

    private final CoffeeService coffeeService;

    @GetMapping("/coffee/{name}")
    public CoffeeResponse make(@PathVariable String name, @RequestParam("size") String size) {
        log.info("Making coffee {} {}...", size, name);

        return coffeeService.make(name, size);
    }

    @GetMapping("/coffee/types")
    public List<CoffeeType> findAllTypes() {
        return coffeeService.coffeeTypes().stream().toList();
    }

    @GetMapping("/coffee/sizes")
    public List<CoffeeSize> findAllSizes() {
        return coffeeService.coffeeSizes().stream().toList();
    }

    @GetMapping("/coffee")
    public ResponseEntity<HttpStatus> findAll() {
        List<CoffeeType> coffeeTypes = coffeeService.coffeeTypes().stream().toList();
        List<CoffeeSize> coffeeSize = coffeeService.coffeeSizes().stream().toList();

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
