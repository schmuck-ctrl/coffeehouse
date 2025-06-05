package de.detim.techtalk.observability.coffeetype.controller;

import de.detim.techtalk.observability.coffeetype.repo.CoffeeType;
import de.detim.techtalk.observability.coffeetype.service.CoffeeTypeService;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coffee-types")
public class CoffeeTypeController {

    private final CoffeeTypeService coffeeTypeService;

    @GetMapping
    public List<CoffeeType> findAll() {
        return coffeeTypeService.findAll();
    }

    @GetMapping("/search/findByName")
    public CoffeeType findByName(@RequestParam("name") String name) {
        return coffeeTypeService.findByName(name);
    }

    public void anotherMethod() {
        Span currentSpan = Span.current();
        currentSpan.addEvent("\"ADD EVENT TO tracedMethodWithName SPAN\"");
        currentSpan.setAttribute("isTestAttribute", true);
        currentSpan.setStatus(StatusCode.OK);
    }
}
