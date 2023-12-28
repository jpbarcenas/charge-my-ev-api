package com.myev.charge.exception;

import com.myev.charge.payload.ErrorDetailsVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // handle specific exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailsVO> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                          WebRequest request) {
        ErrorDetailsVO errorDetails = new ErrorDetailsVO(new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateLocationException.class)
    public ResponseEntity<ErrorDetailsVO> handleDuplicateLocationException(DuplicateLocationException ex,
                                                                         WebRequest request)
    {
        ErrorDetailsVO errorDetails = new ErrorDetailsVO(new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MaxChargingPointsReachedException.class)
    public ResponseEntity<ErrorDetailsVO> handleMaxChargingPointsReachedException(MaxChargingPointsReachedException ex,
                                                                           WebRequest request)
    {
        ErrorDetailsVO errorDetails = new ErrorDetailsVO(new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

    // handle global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsVO> handleGlobalException(Exception ex,
                                                              WebRequest request)
    {
        ErrorDetailsVO errorDetails = new ErrorDetailsVO(new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });


        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
