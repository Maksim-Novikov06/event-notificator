package com.paradise.eventnotificator.exception;


import java.time.LocalDateTime;

public record ErrorMessageResponse (
        String message,
        String detailMessage,
        LocalDateTime dateTime
){


}
