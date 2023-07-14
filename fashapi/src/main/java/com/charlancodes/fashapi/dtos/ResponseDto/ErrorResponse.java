package com.charlancodes.fashapi.dtos.ResponseDto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    private String message;
    private HttpStatus httpStatus;
    private String debugMessage;
}
