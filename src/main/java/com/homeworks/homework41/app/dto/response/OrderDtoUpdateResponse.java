package com.homeworks.homework41.app.dto.response;

import com.homeworks.homework41.app.entity.Order;
import org.springframework.http.HttpStatus;

public record OrderDtoUpdateResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        Order order) {

    public static final String SUCCESS_MESSAGE = "Order with id %s has been updated successfully.";
    public static final String FAILURE_MESSAGE = "Order with id %s has not been found!";

    public static OrderDtoUpdateResponse of(Long id, boolean isUserFound, Order orderUpdated) {
        if (isUserFound)
            return new OrderDtoUpdateResponse(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE.formatted(id), orderUpdated);
        else
            return new OrderDtoUpdateResponse(
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE.formatted(id), null);
    }
}

