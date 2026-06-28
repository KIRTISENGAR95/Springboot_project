package com.api.billhostpro.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResourceNotFoundExceptionTest {

    @Test
    void constructorShouldSetMessage() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Item Not Found");

        assertEquals("Item Not Found", exception.getMessage());
    }
}
