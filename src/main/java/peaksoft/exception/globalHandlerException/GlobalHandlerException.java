package peaksoft.exception.globalHandlerException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import peaksoft.exception.*;
import peaksoft.validation.PasswordValidation;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse notFound(NotFoundException n) {
        return new ExceptionResponse(HttpStatus.NOT_FOUND, n.getClass().getName(), n.getMessage());
    }
    @ExceptionHandler(InvalidNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse exists(InvalidNameException i){
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, i.getClass().getName(), i.getMessage());
    }
    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse exists(InvalidEmailException i){
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, i.getClass().getName(), i.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse exists(MethodArgumentNotValidException i){
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, i.getClass().getName(), i.getMessage());
    }
    @ExceptionHandler(AlreadyExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse exists(AlreadyExists i){
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, i.getClass().getName(), i.getMessage());
    }

    @ExceptionHandler(AccessDenied.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse accessDenied(AccessDenied i){
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, i.getClass().getName(), i.getMessage());
    }
}
