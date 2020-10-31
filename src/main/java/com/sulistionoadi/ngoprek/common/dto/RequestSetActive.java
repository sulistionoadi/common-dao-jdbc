package com.sulistionoadi.ngoprek.common.dto;

import java.io.Serializable;

public class RequestSetActive<ID> implements Serializable {

	private static final long serialVersionUID = -8911506704709679293L;
	private ID id;
	private StatusActive status;
	private String updatedBy;
	
	public RequestSetActive(ID id, StatusActive status) {
		this.id = id;
		this.status = status;
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
	public StatusActive getStatus() {
		return status;
	}
	public void setStatus(StatusActive status) {
		this.status = status;
	}
	
}
