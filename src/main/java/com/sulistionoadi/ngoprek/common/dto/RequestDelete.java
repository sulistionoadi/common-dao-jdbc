package com.sulistionoadi.ngoprek.common.dto;

import java.io.Serializable;

public class RequestDelete<ID> implements Serializable {

	private static final long serialVersionUID = 5978728424377748627L;
	private ID id;
	private String updatedBy;
	
	public RequestDelete(ID id) {
		this.id = id;
	}
	
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
}
