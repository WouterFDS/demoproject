package com.example.demoproject.config;

import com.example.demoproject.domain.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ApiResponse handleNotFoundException(RuntimeException ex) {
        ApiResponse apiResponse = new ApiResponse(400, "Bad request Server", null);
        return apiResponse;
    }

}
