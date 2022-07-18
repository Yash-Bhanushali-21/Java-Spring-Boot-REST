package com.service.payroll.exception;

import java.util.NoSuchElementException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.common.net.HttpHeaders;

@ControllerAdvice
public class CustomControllerAdvicer extends ResponseEntityExceptionHandler{
	
	    @ResponseBody
	    @ExceptionHandler(NoSuchElementException.class)
	    public ResponseEntity<?> handleControllerException(HttpServletRequest request, NoSuchElementException ex) {
	        CustomExcepetionBody customExp = new CustomExcepetionBody(HttpStatus.NOT_FOUND.value(), "Not Found!");
	        return new ResponseEntity<CustomExcepetionBody>(customExp,HttpStatus.NOT_FOUND);
	    }
	    
	    @ResponseBody
	    @ExceptionHandler(InvalidHikePercentageException.class)
	    public ResponseEntity<?> handleEmployeeNotFoundException(HttpServletRequest request, InvalidHikePercentageException ex) {
	        CustomExcepetionBody customExp = new CustomExcepetionBody(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	        return new ResponseEntity<CustomExcepetionBody>(customExp,HttpStatus.BAD_REQUEST);
	    }
	    
	    
	   

	  

	
}
