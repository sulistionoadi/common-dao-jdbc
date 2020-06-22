/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sulistionoadi.ngoprek.common.helper;

import java.text.MessageFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author adi
 */
public class DateHelper {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(DateHelper.class);
    
    public static Date dateWithMinTime(Date startDate){
        Instant start = Instant.ofEpochMilli(startDate.getTime());
        LocalDateTime localdt = LocalDateTime.ofInstant(start, ZoneId.systemDefault());
        localdt = localdt.with(ChronoField.MILLI_OF_DAY, 0);
        return new Date(localdt.toInstant(localdt.atZone(ZoneId.systemDefault()).getOffset()).toEpochMilli());
    }
    
    public static Date dateWithMaxTime(Date endDate){
        Instant start = Instant.ofEpochMilli(endDate.getTime());
        LocalDateTime localdt = LocalDateTime.ofInstant(start, ZoneId.systemDefault());
        localdt = localdt.plus(1, ChronoUnit.DAYS).with(ChronoField.MILLI_OF_DAY, 0).minus(1, ChronoUnit.SECONDS);
        return new Date(localdt.toInstant(localdt.atZone(ZoneId.systemDefault()).getOffset()).toEpochMilli());
    }
    
    public static Date minDateOfMonth(Date date){
        Instant i = Instant.ofEpochMilli(date.getTime());
        LocalDateTime localdt = LocalDateTime.ofInstant(i, ZoneId.systemDefault())
                .with(ChronoField.DAY_OF_MONTH, 1)
                .with(ChronoField.MILLI_OF_DAY, 0);
        return new Date(localdt.toInstant(localdt.atZone(ZoneId.systemDefault()).getOffset()).toEpochMilli());
    }
    
    public static Date maxDateOfMonth(Date date){
        Instant i = Instant.ofEpochMilli(date.getTime());
        LocalDateTime localdt = LocalDateTime.ofInstant(i, ZoneId.systemDefault())
                .plus(1, ChronoUnit.MONTHS)
                .with(ChronoField.DAY_OF_MONTH, 1)
                .with(ChronoField.MILLI_OF_DAY, 0)
                .minus(1, ChronoUnit.SECONDS);
        return new Date(localdt.toInstant(localdt.atZone(ZoneId.systemDefault()).getOffset()).toEpochMilli());
    }
    
    public static Date calculateDate(Date date, Integer value, ChronoUnit unit){
        return calculateDate(convertDate2LocalDateTime(date, 0), value, unit);
    }
    
    public static Date calculateDate(LocalDateTime ldt, Integer value, ChronoUnit unit){
        ldt = ldt.plus(value, unit);
        return convertToDate(ldt);
    }
    
    public static LocalDateTime calculateLocalDateTime(LocalDateTime ldt, Integer value, ChronoUnit unit){
        return ldt.plus(value, unit);
    }
    
    public static String dateToString(Date date, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withZone(ZoneId.systemDefault());
        return formatter.format(Instant.ofEpochMilli(date.getTime()));
    }
    
    public static Date stringToDate(String strDate, String format){
        ZoneId zone = ZoneId.systemDefault();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withZone(zone);
        
        LocalDate ld = LocalDate.parse(strDate, formatter);
        ZonedDateTime zonedt = ld.atStartOfDay(zone);
        
        return new Date(zonedt.toInstant().toEpochMilli());
    }
    
    public static LocalDate stringToLocalDate(String strDate, String format){
        ZoneId zone = ZoneId.systemDefault();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withZone(zone);
        
        return LocalDate.parse(strDate, formatter);
    }
    
    public static Date stringToTime(String strDate, String format){
        ZoneId zone = ZoneId.systemDefault();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withZone(zone);
        
        LocalTime lt = LocalTime.parse(strDate, formatter);
        ZonedDateTime zonedt = lt.atDate(LocalDate.now()).atZone(zone);
        return new Date(zonedt.toInstant().toEpochMilli());
    }
    
    public static Date convertToDate(LocalDateTime localDateTime){
        return new Date(localDateTime.toInstant(localDateTime.atZone(ZoneId.systemDefault()).getOffset()).toEpochMilli());
    }
    
    public static Date dateWithField(LocalDateTime ldt, ChronoField field, Integer value){
        ldt = ldt.with(field, value);
        return convertToDate(ldt);
    }
    
    public static LocalDateTime localDateTimeWithField(LocalDateTime ldt, ChronoField field, Integer value){
        return ldt.with(field, value);
    }
    
    public static Instant calculateDate(Date start, Long inc, ChronoField field, Long valueField){
        
        DateTimeFormatter fmtLengkap = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS").withZone(ZoneId.systemDefault());
        
        Instant startCall = Instant.ofEpochMilli(start.getTime());
        if(field!=null){
            startCall = startCall.with(field, valueField);
        }
        
        LOGGER.debug("CALCULATE SDATE [{}] PLUS [{}] ", fmtLengkap.format(startCall), inc);
        
        Instant endCall = startCall.plusSeconds(inc);
        
        if(field!=null){
            endCall = endCall.with(field, valueField);
        }
        
        LOGGER.debug("CALCULATE EDATE [{}]  ", fmtLengkap.format(endCall));
        return endCall;
    }
    
    public static Date convertToDate(Instant instant){
        return new Date(instant.toEpochMilli());
    }
    
    public static LocalDateTime convertDate2LocalDateTime(Date date, Integer nanos){
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        if(nanos!=null){
            ldt = ldt.withNano(nanos);
        }
        
        return ldt;
    }
    
    /**
     * 
     * @param date1
     * @param date2
     * @param spareMillis
     * @return timediff in milliseconds
     */
    public static Long compare(Date date1, Date date2, Integer spareMillis){
        Instant i1 = Instant.ofEpochMilli(date1.getTime());
        Instant i2 = Instant.ofEpochMilli(date2.getTime());
        
        return compare(i1, i2, spareMillis);
    }
    
    public static Long compare(Instant i1, Instant i2, Integer spareMillis){
        return (i1.toEpochMilli() + spareMillis) - i2.toEpochMilli();
    }
    
    public static Date weekToDate(String strDate) throws Exception {
    	String[] splited = strDate.split(","); //Format value must be (WeekXX, yyyy)
    	
    	if(splited.length < 2) {
    		throw new Exception("Invalid week value");
    	}
    	
    	try {
    		Calendar cal = Calendar.getInstance();
    		cal.set(Calendar.YEAR, Integer.valueOf(splited[1].trim()));
    		cal.set(Calendar.WEEK_OF_YEAR, Integer.valueOf(splited[0].substring(4).trim()));        
    		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    		
    		return cal.getTime();
    	} catch (Exception ex) {
    		throw new Exception( 
    				MessageFormat.format("Cannot getDate from value {0}", strDate), 
    				ex);
    	}
    }
    
    public static Long monthsDiff(Date date1, Date date2) {
    	LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	
    	Long result = ChronoUnit.MONTHS.between(localDate1, localDate2);
    	LOGGER.debug("LocalDate-1:{} LocalDate-2:{} different months is {}", 
    			localDate1.toString(), localDate2.toString(), result);
    	return result;
    }
    
    public static Long daysDiff(Date date1, Date date2) {
    	LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	
    	Long result = ChronoUnit.DAYS.between(localDate1, localDate2);
    	LOGGER.debug("LocalDate-1:{} LocalDate-2:{} different days is {}", 
    			localDate1.toString(), localDate2.toString(), result);
    	return result;
    }
    
    public static String retentionMonths(Date date, Integer numOfDays) {
    	Date lastDate = calculateDate(date, numOfDays, ChronoUnit.DAYS);
    	LOGGER.debug("The trxDate:{} after add {} days is {}", 
    			dateToString(date, "yyyy-MM-dd"), numOfDays, dateToString(lastDate, "yyyy-MM-dd"));
    	
    	Long diffMonths = monthsDiff(date, lastDate);
    	Date monthAdded = calculateDate(date, diffMonths.intValue(), ChronoUnit.MONTHS);
    	LOGGER.debug("The trxDate:{} after add {} months is {}", 
    			dateToString(date, "yyyy-MM-dd"), diffMonths, dateToString(monthAdded, "yyyy-MM-dd"));
    	
    	Long diffDays = daysDiff(monthAdded, lastDate);
    	
    	StringBuilder sb = new StringBuilder();
    	if(diffMonths > 0) {
    		String units = diffMonths > 1 ? "months" : "month";
    		sb.append(MessageFormat.format("{0}{1}", diffMonths, units));
    	}
    	if(StringUtils.isNotBlank(sb.toString())) {
    		sb.append(" ");
    	}
    	if(diffDays > 0) {
    		String units = diffDays > 1 ? "days" : "day";
    		sb.append(MessageFormat.format("{0}{1}", diffDays, units));
    	}
    	return sb.toString();
    }
    
}
