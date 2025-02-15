package com.homeworks.homework41.app.dto.response;

import org.springframework.http.HttpStatus;

public record OrderDtoDeleteRequest(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message) {

    public static final String SUCCESS_MESSAGE = "Order with id %s has been deleted successfully.";
    public static final String FAILURE_MESSAGE = "Order with id %s has not been found!";

    public static OrderDtoDeleteRequest of(Long id, boolean isUserFound) {
        if (isUserFound)
            return new OrderDtoDeleteRequest(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE.formatted(id));
        else
            return new OrderDtoDeleteRequest(
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE.formatted(id));
    }
}
