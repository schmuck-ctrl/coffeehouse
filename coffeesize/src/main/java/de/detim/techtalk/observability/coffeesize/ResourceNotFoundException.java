package de.detim.techtalk.observability.coffeesize;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String name) {
        super("Resource not found: " + name);
    }
}
