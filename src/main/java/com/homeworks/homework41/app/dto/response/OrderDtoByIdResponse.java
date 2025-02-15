package com.homeworks.homework41.app.dto.response;

import com.homeworks.homework41.app.entity.Order;
import org.springframework.http.HttpStatus;

public record OrderDtoByIdResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        Order order) {

    public static final String SUCCESS_MESSAGE = "Order with id %s has been fetched successfully.";
    public static final String FAILURE_MESSAGE = "Order with id %s has not been found!";

    public static OrderDtoByIdResponse of(Long id, boolean isFound, Order order) {
        if (isFound)
            return new OrderDtoByIdResponse(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE.formatted(id), order);
        else
            return new OrderDtoByIdResponse(
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE.formatted(id), null);
    }
}

