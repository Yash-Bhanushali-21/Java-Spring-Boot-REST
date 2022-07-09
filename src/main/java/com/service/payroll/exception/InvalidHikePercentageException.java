package com.service.payroll.exception;

import java.util.NoSuchElementException;

public class InvalidHikePercentageException extends NoSuchElementException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidHikePercentageException(String msg) {
		super(msg);
	}
}
