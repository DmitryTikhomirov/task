package com.kupreychik.conrtoller.handler;

import com.kupreychik.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Problem> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(buildProblem(ex, request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundExceptionException(ResourceNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>("Call ur backend dev", HttpStatusCode.valueOf(404));
    }

    private Problem buildProblem(Exception ex, WebRequest request) {
        return Problem.builder()
                .withType(URI.create(request.getContextPath()))
                .withTitle(ex.getLocalizedMessage())
                .with("hello", "anton")
                .withStatus(Status.INTERNAL_SERVER_ERROR)
                .withDetail(ex.getMessage())
                .build();
    }

}
