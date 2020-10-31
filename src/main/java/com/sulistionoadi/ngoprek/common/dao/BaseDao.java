package com.sulistionoadi.ngoprek.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.sulistionoadi.ngoprek.common.dto.RequestDelete;
import com.sulistionoadi.ngoprek.common.dto.RequestSetActive;
import com.sulistionoadi.ngoprek.common.dto.StatusActive;
import com.sulistionoadi.ngoprek.common.pss.dto.PssFilter;

public interface BaseDao<T, ID> extends Serializable {

	public void save(T t);
	public void update(T t);
	public Optional<T> findOne(ID id);
	public Long count(PssFilter filter, StatusActive statusActive);
	public List<T> filter(PssFilter filter, StatusActive statusActive);
	public void delete(RequestDelete<ID> req);
	public void setActive(RequestSetActive<ID> req);
	
}
