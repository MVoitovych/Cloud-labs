package ua.lviv.iot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.lviv.iot.exeption.CallNotFoundException;
import ua.lviv.iot.exeption.DepartureNotFoundException;
import ua.lviv.iot.exeption.FirecarNotFoundException;
import ua.lviv.iot.exeption.FiremanNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(DepartureNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cityNotFoundHandler(DepartureNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CallNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String personNotFoundHandler(CallNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(FiremanNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bookNotFoundHandler(FiremanNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(FirecarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String logNotFoundHandler(FirecarNotFoundException ex) {
        return ex.getMessage();
    }
}
