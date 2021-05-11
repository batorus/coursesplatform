package com.batorus.coursesplatform.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ErrorAdvice {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ApiError handleMethodArgNotValid(MethodArgumentNotValidException exception, HttpServletRequest request) {
//        ApiError error = new ApiError(400, exception.getMessage(), request.getServletPath());
//        BindingResult bindingResult = exception.getBindingResult();
//        Map<String, String> validationErrors = new HashMap<>();
//        for (FieldError fieldError : bindingResult.getFieldErrors()) {
//            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
//        }
//        error.setValidationErrors(validationErrors);
//        return error;
//    }

    @Autowired
    private ErrorAttributes errorAttributes;

    @ExceptionHandler({MethodArgumentNotValidException.class,
                       ResourceNotFoundException.class,
                       HttpRequestMethodNotSupportedException.class,
                       DataIntegrityViolationException.class})
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ApiError> handleError(WebRequest webRequest, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> attributes = this.errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE, ErrorAttributeOptions.Include.BINDING_ERRORS));
        String message = (String) attributes.get("message");
        String path = request.getServletPath();

        response.setStatus(400);
        int status = response.getStatus();

        ApiError error = new ApiError(status, message, path);

        if (attributes.containsKey("errors")) {
            @SuppressWarnings("unchecked")
            List<FieldError> fieldErrors = (List<FieldError>) attributes.get("errors");
            Map<String, String> validationErrors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            error.setValidationErrors(validationErrors);
        }

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
