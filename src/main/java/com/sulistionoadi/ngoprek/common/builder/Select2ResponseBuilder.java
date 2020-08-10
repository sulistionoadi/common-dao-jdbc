package com.sulistionoadi.ngoprek.common.builder;

import com.sulistionoadi.ngoprek.common.dto.Select2Response;

public class Select2ResponseBuilder {

	private String code;
	private String message;
	private Long totalCount;
	private Boolean incompleteResults;
	private Object items;

	public static Select2ResponseBuilder builder() {
		return new Select2ResponseBuilder();
	}

	public Select2ResponseBuilder setCode(String code) {
		this.code = code;
		return this;
	}

	public Select2ResponseBuilder setMessage(String message) {
		this.message = message;
		return this;
	}

	public Select2ResponseBuilder setItems(Object items) {
		this.items = items;
		return this;
	}
	
	public Select2ResponseBuilder setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
		return this;
	}
	
	public Select2ResponseBuilder setIncompleteResults(Boolean incompleteResults) {
		this.incompleteResults = incompleteResults;
		return this;
	}

	public Select2Response build() {
		return new Select2Response(this.code, this.message, this.totalCount, this.incompleteResults, this.items);
	}

}
