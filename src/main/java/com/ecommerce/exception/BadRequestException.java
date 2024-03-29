package com.ecommerce.exception;

import com.ecommerce.dto.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private MessageResponse messageResponse;

    public BadRequestException(MessageResponse messageResponse) {
        super();
        this.messageResponse = messageResponse;
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageResponse getMessageResponse() {
        return messageResponse;
    }
}