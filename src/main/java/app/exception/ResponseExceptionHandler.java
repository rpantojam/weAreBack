package app.exception;

import java.util.Date;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	   @ExceptionHandler(Exception.class)
	   protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
	       ResponseException exceptionResponse = new ResponseException(new Date(), ex.getMessage(), request.getDescription(false));
	       return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	   }

	}