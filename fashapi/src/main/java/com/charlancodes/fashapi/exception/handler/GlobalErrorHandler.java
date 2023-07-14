package com.charlancodes.fashapi.exception.handler;

import com.charlancodes.fashapi.dtos.ResponseDto.ErrorResponse;
import com.charlancodes.fashapi.exception.CustomAppException;
import com.charlancodes.fashapi.exception.ResourceAlreadyAvailableException;
import com.charlancodes.fashapi.exception.ResourceNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {
@ExceptionHandler({ResourceNotExistException.class})
    public ResponseEntity<?> handleResourceNotExistException (final ResourceNotExistException exception){
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setDebugMessage("Resources does not Exist");
    errorResponse.setMessage(exception.getMessage());
    errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
}
    @ExceptionHandler({ResourceAlreadyAvailableException.class})
    public ResponseEntity<?> handleResourceAlreadyAvailableException (final ResourceAlreadyAvailableException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setDebugMessage("Resources already Exist");
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
    @ExceptionHandler({CustomAppException.class})
    public ResponseEntity<?> handleCustomAppException (final CustomAppException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setDebugMessage("Product out of Stock");
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
