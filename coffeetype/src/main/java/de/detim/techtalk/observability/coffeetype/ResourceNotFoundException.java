package de.detim.techtalk.observability.coffeetype;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String name) {
        super("Resource not found: " + name);
    }
}
