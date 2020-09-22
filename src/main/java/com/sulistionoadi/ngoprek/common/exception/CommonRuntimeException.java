package com.sulistionoadi.ngoprek.common.exception;

public class CommonRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -7162899717202710603L;

	public CommonRuntimeException(String code, String message, Throwable cause) {
		super(code.concat("|").concat(message).concat(", Cause: ").concat(cause.getMessage()), cause);
	}

	public CommonRuntimeException(String code, String message) {
		super(code.concat("|").concat(message));
	}
	
	public CommonRuntimeException(String message, Throwable cause) {
		super(message.concat(", Cause: ").concat(cause.getMessage()), cause);
	}
	
}
