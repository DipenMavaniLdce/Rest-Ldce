package com.ldce.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ldce.filter.JwtAuthenticationEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ldce.exception.ErrorResponse;
import com.ldce.exception.RecordNotFoundException;
import com.ldce.exception.UserNotAllowedToAccess;
import com.ldce.exception.ValidationFailException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request) {
		Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
		logger.error(ex.toString());
		Map<Object,Object> res =new HashMap<>();
		res.put("message",ex.getMessage());
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
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
	public final ResponseEntity<?> validationFailException(ValidationFailException ex, WebRequest request) {
		List<String> details = new ArrayList<>();

		details.add(ex.getLocalizedMessage());
		HashMap<String,String> res = new HashMap<>();
		res.put("error","validation fail plz check your data");
		res.put("details",details.toString());

		return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<Object> dataIntegrityViolationException(DataIntegrityViolationException ex,
			WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("bad request", details);
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		//return super.handleMissingServletRequestParameter(ex, headers, status, request);
		Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
		logger.error(ex.toString());
		Map<Object,Object> res =new HashMap<>();
		res.put("message",ex.getMessage());
		return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
		Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
		logger.error(ex.toString());
        Map<Object,Object> res =new HashMap<>();
        return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);

	}
}
