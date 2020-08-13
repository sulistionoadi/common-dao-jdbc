package com.sulistionoadi.ngoprek.common.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RowMapperUtils {

	private final static Logger log = LoggerFactory.getLogger(RowMapperUtils.class);
	
	public static Boolean getBooleanValue(ResultSet rs, int rowNum, String columnName) throws SQLException {
		Integer columnVal = rs.getInt(columnName);

		log.debug("Check Boolean Value for column:{} value:{}", columnName, columnVal);
		if (columnVal != null) {
			switch (columnVal) {
				case 0:
					return Boolean.FALSE;
				case 1:
					return Boolean.TRUE;
				default:
					throwError(columnName, columnVal, rowNum);
			}
		}
		return Boolean.FALSE;
	}
	
	public static Date getDateValue(ResultSet rs, int rowNum, String columnName) throws SQLException {
		java.sql.Date columnVal = rs.getDate(columnName);
		return columnVal!=null ? new Date(columnVal.getTime()) : null;
	}
	
	public static void throwError(String columnName, Object columnVal, int rowNum) {
		log.warn("Invalid value {}={} at row:{}", columnName, columnVal, rowNum);
		throw new IllegalStateException(MessageFormat.format("Invalid value {0}={1} at row:{2}", columnName,
				columnVal!=null ? columnVal.toString() : "null", String.valueOf(rowNum)));
	}

}
