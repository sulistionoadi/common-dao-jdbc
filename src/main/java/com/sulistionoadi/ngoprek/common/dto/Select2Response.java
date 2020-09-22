package com.sulistionoadi.ngoprek.common.dto;

public class Select2Response {
	
	private String code;
	private String message;
	private Long total_count;
	private Boolean incomplete_results;
	private Object items;

	public Select2Response() {}

	public Select2Response(String code, String message, Long total_count, Boolean incomplete_results, Object items) {
		this.code = code;
		this.message = message;
		this.total_count = total_count;
		this.incomplete_results = incomplete_results;
		this.items = items;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTotal_count() {
		return total_count;
	}

	public void setTotal_count(Long total_count) {
		this.total_count = total_count;
	}

	public Boolean getIncomplete_results() {
		return incomplete_results;
	}

	public void setIncomplete_results(Boolean incomplete_results) {
		this.incomplete_results = incomplete_results;
	}

	public Object getItems() {
		return items;
	}

	public void setItems(Object items) {
		this.items = items;
	}

}
