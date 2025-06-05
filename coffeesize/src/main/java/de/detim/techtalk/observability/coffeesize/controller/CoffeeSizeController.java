package de.detim.techtalk.observability.coffeesize.controller;

import de.detim.techtalk.observability.coffeesize.repo.CoffeeSize;
import de.detim.techtalk.observability.coffeesize.service.CoffeeSizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coffee-sizes")
public class CoffeeSizeController {

    private final CoffeeSizeService coffeeSizeService;

    @GetMapping
    public List<CoffeeSize> findAll() {
        return coffeeSizeService.findAll();
    }

    @GetMapping("/search/findByName")
    public CoffeeSize findByName(@RequestParam("name") String name) {
        return coffeeSizeService.findByName(name);
    }
}
