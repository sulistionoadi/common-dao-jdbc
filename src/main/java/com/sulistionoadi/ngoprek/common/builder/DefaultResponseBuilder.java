package com.sulistionoadi.ngoprek.common.builder;

import com.sulistionoadi.ngoprek.common.dto.DefaultResponse;

public class DefaultResponseBuilder {

	private String code;
	private String message;
	private Object data;

	public static DefaultResponseBuilder builder() {
		return new DefaultResponseBuilder();
	}

	public DefaultResponseBuilder setCode(String code) {
		this.code = code;
		return this;
	}

	public DefaultResponseBuilder setMessage(String message) {
		this.message = message;
		return this;
	}

	public DefaultResponseBuilder setData(Object data) {
		this.data = data;
		return this;
	}

	public DefaultResponse build() {
		return new DefaultResponse(this.code, this.message, this.data);
	}

}
