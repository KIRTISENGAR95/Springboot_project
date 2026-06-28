package com.api.billhostpro.exception;

import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void handleResourceNotFoundShouldReturnNotFoundResponse() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Item Not Found");

        ResponseEntity<Map<String, Object>> response =
                globalExceptionHandler.handleResourceNotFound(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().get("status"));
        assertEquals("Item Not Found", response.getBody().get("message"));
        assertNotNull(response.getBody().get("timestamp"));
    }

    @Test
    void handleValidationShouldReturnBadRequestResponse() throws NoSuchMethodException {
        MethodArgumentNotValidException exception = createMethodArgumentNotValidException();

        ResponseEntity<Map<String, Object>> response =
                globalExceptionHandler.handleValidation(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().get("status"));
        assertEquals("Item Name is required", response.getBody().get("message"));
        assertNotNull(response.getBody().get("timestamp"));
    }

    @Test
    void handleExceptionShouldReturnInternalServerErrorResponse() {
        Exception exception = new Exception("Unexpected error");

        ResponseEntity<Map<String, Object>> response =
                globalExceptionHandler.handleException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().get("status"));
        assertEquals("Unexpected error", response.getBody().get("message"));
        assertNotNull(response.getBody().get("timestamp"));
    }

    private MethodArgumentNotValidException createMethodArgumentNotValidException()
            throws NoSuchMethodException {
        ItemValidationTarget target = new ItemValidationTarget();
        BeanPropertyBindingResult bindingResult =
                new BeanPropertyBindingResult(target, "itemValidationTarget");
        bindingResult.addError(new FieldError(
                "itemValidationTarget",
                "itemName",
                "Item Name is required"
        ));

        Method method = ItemValidationTarget.class.getDeclaredMethod("setItemName", String.class);
        MethodParameter methodParameter = new MethodParameter(method, 0);
        return new MethodArgumentNotValidException(methodParameter, bindingResult);
    }

    private static class ItemValidationTarget {

        @SuppressWarnings("unused")
        public void setItemName(String itemName) {
        }
    }
}
