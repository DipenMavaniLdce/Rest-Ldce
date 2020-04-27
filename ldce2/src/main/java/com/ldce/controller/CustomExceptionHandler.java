package com.ldce.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ldce.exception.ErrorResponse;
import com.ldce.exception.RecordNotFoundException;
import com.ldce.exception.UserNotAllowedToAccess;
import com.ldce.exception.ValidationFailException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	  @ExceptionHandler(Exception.class)
	    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        ErrorResponse error = new ErrorResponse("Server Error", details);
	        return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
	    @ExceptionHandler(RecordNotFoundException.class)
	    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(RecordNotFoundException ex) {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        ErrorResponse error = new ErrorResponse("Record Not Found", details);
	        System.out.println("inside");
	        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	    }
	    @ExceptionHandler(UserNotAllowedToAccess.class)
	    public final ResponseEntity<Object> handleUserNotAllowedToAccess(UserNotAllowedToAccess ex, WebRequest request) {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        ErrorResponse error = new ErrorResponse("User Not Allowed To Access", details);
	        return new ResponseEntity(error, HttpStatus.FORBIDDEN);
	    }
	    
	    @ExceptionHandler(ValidationFailException.class)
	    public final ResponseEntity<Object> validationFailException(ValidationFailException ex, WebRequest request) {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        ErrorResponse error = new ErrorResponse("bad request", details);
	        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	    }
	    
	    @ExceptionHandler(DataIntegrityViolationException.class)
	    public final ResponseEntity<Object> dataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        ErrorResponse error = new ErrorResponse("bad request", details);
	        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	    }
}

