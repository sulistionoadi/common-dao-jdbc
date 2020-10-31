package com.sulistionoadi.ngoprek.common.dao;

import java.util.List;
import java.util.Optional;

import com.sulistionoadi.ngoprek.common.dto.RequestDelete;
import com.sulistionoadi.ngoprek.common.dto.RequestSetActive;
import com.sulistionoadi.ngoprek.common.dto.StatusActive;
import com.sulistionoadi.ngoprek.common.exception.CommonRuntimeException;
import com.sulistionoadi.ngoprek.common.pss.dto.PssFilter;

public interface BaseDao<T, ID> {

	public void save(T t) throws CommonRuntimeException;
	public void update(T t) throws CommonRuntimeException;
	public Optional<T> findOne(ID id) throws CommonRuntimeException;
	public Long count(PssFilter filter, StatusActive statusActive);
	public List<T> filter(PssFilter filter, StatusActive statusActive);
	public void delete(RequestDelete<ID> req);
	public void setAsDelete(RequestDelete<ID> req);
	public void setActive(RequestSetActive<ID> req);
	
}
