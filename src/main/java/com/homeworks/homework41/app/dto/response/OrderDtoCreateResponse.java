package com.homeworks.homework41.app.dto.response;

import com.homeworks.homework41.app.entity.Order;
import org.springframework.http.HttpStatus;

public record OrderDtoCreateResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        Order order) {

    public static final String SUCCESS_MESSAGE = "Order has been created successfully!";
    public static final String FAILURE_MESSAGE = "Order has not been created!";

    public static OrderDtoCreateResponse of(boolean isCreated, Order order){
        if (isCreated) {
            return new OrderDtoCreateResponse(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    true,
                    SUCCESS_MESSAGE,
                    order);
        } else {
            return new OrderDtoCreateResponse(
                    HttpStatus.NO_CONTENT.value(),
                    HttpStatus.NO_CONTENT.getReasonPhrase(),
                    false,
                    FAILURE_MESSAGE,
                    null
            );
        }
    }
}
