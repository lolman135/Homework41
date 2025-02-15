package com.homeworks.homework41.app.dto.response;

import com.homeworks.homework41.app.entity.Order;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public record OrderDtoListResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        List<Order> orders) {

    public static final String SUCCESS_MESSAGE = "Order list has been fetched successfully.";
    public static final String FAILURE_MESSAGE = "Order list has not been found!";

    public static OrderDtoListResponse of(boolean isListEmpty, List<Order> orders) {
        if (isListEmpty)
            return new OrderDtoListResponse(
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    false, SUCCESS_MESSAGE, Collections.emptyList());
        else
            return new OrderDtoListResponse(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    true, FAILURE_MESSAGE, orders);
    }
}