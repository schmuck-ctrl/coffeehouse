package de.detim.techtalk.observability.coffeesize.service;

import de.detim.techtalk.observability.coffeesize.ResourceNotFoundException;
import de.detim.techtalk.observability.coffeesize.repo.CoffeeSize;
import de.detim.techtalk.observability.coffeesize.repo.CoffeeSizeRepository;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeSizeService {

    private final CoffeeSizeRepository coffeeSizeRepository;
    private final OpenTelemetry openTelemetry;
    private Tracer tracer;


    public CoffeeSizeService(CoffeeSizeRepository coffeeSizeRepository, OpenTelemetry openTelemetry) {
        this.coffeeSizeRepository = coffeeSizeRepository;
        this.openTelemetry = openTelemetry;

        createTracer();
    }

    public List<CoffeeSize> findAll() {
        return coffeeSizeRepository.findAll();
    }

    public CoffeeSize findByName(String name) {;
        Span span = tracer.spanBuilder("CoffeeSizeService.findByName")
                .setAttribute("name", name)
                .startSpan();
        try (Scope ignored = span.makeCurrent()) {
            return coffeeSizeRepository.findByName(name)
                    .orElseThrow(() -> new ResourceNotFoundException("coffee-type with name: " + name));
        } catch (ResourceNotFoundException e) {
            span.setStatus(StatusCode.ERROR);
            throw new ResourceNotFoundException("coffee-type with name: " + name);
        } finally {
            span.end();
        }
    }

    private void createTracer(){
        this.tracer = openTelemetry.getTracer("CoffeeTypeServiceTracer");
    }
}
