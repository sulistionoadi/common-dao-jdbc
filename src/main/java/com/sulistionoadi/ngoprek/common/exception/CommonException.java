package com.sulistionoadi.ngoprek.common.exception;

public class CommonException extends Exception {

	private static final long serialVersionUID = -7162899717202710603L;

	public CommonException(String code, String message, Throwable cause) {
		super(code.concat("|").concat(message).concat(", Cause: ").concat(cause.getMessage()), cause);
	}

	public CommonException(String code, String message) {
		super(code.concat("|").concat(message));
	}
	
	public CommonException(String message, Throwable cause) {
		super(message.concat(", Cause: ").concat(cause.getMessage()), cause);
	}
	
}
