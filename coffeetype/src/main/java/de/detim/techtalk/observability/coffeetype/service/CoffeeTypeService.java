package de.detim.techtalk.observability.coffeetype.service;

import de.detim.techtalk.observability.coffeetype.ResourceNotFoundException;
import de.detim.techtalk.observability.coffeetype.repo.CoffeeType;
import de.detim.techtalk.observability.coffeetype.repo.CoffeeTypeRepository;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeTypeService {

    private final CoffeeTypeRepository coffeeTypeRepository;
    private final OpenTelemetry openTelemetry;
    private Tracer tracer;

    public CoffeeTypeService(CoffeeTypeRepository coffeeTypeRepository, OpenTelemetry openTelemetry) {
        this.coffeeTypeRepository = coffeeTypeRepository;
        this.openTelemetry = openTelemetry;

        createTracer();
    }

    public List<CoffeeType> findAll() {
        return coffeeTypeRepository.findAll();
    }

    public CoffeeType findByName(String name) {
        Span span = tracer.spanBuilder("CoffeeTypeService.findByName")
                .setAttribute("name", name)
                .startSpan();
        try (Scope ignored = span.makeCurrent()) {
            return coffeeTypeRepository.findByName(name)
                    .orElseThrow(() -> new ResourceNotFoundException("coffee-type with name: " + name));
        } catch (ResourceNotFoundException e) {
            span.setStatus(StatusCode.ERROR);
            throw new ResourceNotFoundException("coffee-type with name: " + name);
        } finally {
            span.end();
        }
    }

    private void createTracer() {
        this.tracer = openTelemetry.getTracer("CoffeeTypeServiceTracer");
    }
}
