package com.sulistionoadi.ngoprek.common.audit;

import static com.sulistionoadi.ngoprek.common.constant.ErrorCode.*;

import java.beans.Statement;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;

import com.sulistionoadi.ngoprek.common.exception.CommonRuntimeException;
import com.sulistionoadi.ngoprek.common.utils.SessionUtils;

@Aspect
public class DataAuditorAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Before("execution(* *.save(..)) && within(*..service.impl.*)")
	public void beforeSave(JoinPoint joinPoint) {
		Statement stmt;
		Object theDTO = joinPoint.getArgs()[0];
		String dtoClass = theDTO.getClass().getSimpleName();
		log.debug("Before Save {} ", dtoClass);
		
		String principal = SessionUtils.getPrincipalName();
		
		try {
			log.debug("Set CreatedBy in object {} as {}", dtoClass, principal);
			stmt = new Statement(theDTO, "setCreatedBy", new Object[] {principal});
			stmt.execute();
		} catch (Exception e) {
			throw new CommonRuntimeException("Failed inject CreatedBy value", e);
		}
		
		try {
			log.debug("Set CreatedDate in object {} as {}", dtoClass, new Date());
			stmt = new Statement(theDTO, "setCreatedDate", new Object[] {new Date()});
			stmt.execute();
		} catch (Exception e) {
			throw new CommonRuntimeException("Failed inject CreatedDate value", e);
		}
	
	}
	
	@Before("execution(* *.update(..)) && within(*..service.impl.*)")
	public void beforeUpdate(JoinPoint joinPoint) {
		Statement stmt;
		Object theDTO = joinPoint.getArgs()[0];
		String dtoClass = theDTO.getClass().getSimpleName();
		log.debug("Before Update {} ", dtoClass);
		
		String principal = SessionUtils.getPrincipalName();
		
		try {
			log.debug("Set UpdatedBy in object {} as {}", dtoClass, principal);
			stmt = new Statement(theDTO, "setUpdatedBy", new Object[] {principal});
			stmt.execute();
		} catch (Exception e) {
			throw new CommonRuntimeException("Failed inject UpdatedBy value", e);
		}
		
		try {
			log.debug("Set UpdatedDate in object {} as {}", dtoClass, new Date());
			stmt = new Statement(theDTO, "setUpdatedDate", new Object[] {new Date()});
			stmt.execute();
		} catch (Exception e) {
			throw new CommonRuntimeException("Failed inject UpdatedDate value", e);
		}
	}
	
	
	@Around("execution(* *.setAsDelete(..)) && within(*..service.impl.*)")
	public void beforeDelete(ProceedingJoinPoint joinPoint) throws Throwable {
		Object arguments[] = joinPoint.getArgs();
		log.debug("Before setAsDelete({}) ", Arrays.toString(arguments));

		String principal = SessionUtils.getPrincipalName();
		if(arguments.length < 2) {
			throw new CommonRuntimeException(RC_INVALID_PARAMETER, "Number of Argument minimum is 2");
		}
		
		if (joinPoint instanceof MethodInvocationProceedingJoinPoint) {
			MethodInvocationProceedingJoinPoint invoke = (MethodInvocationProceedingJoinPoint) joinPoint;
			arguments[1] = principal;
			
			log.debug("Set new Arguments for method setAsDelete({}) ", Arrays.toString(arguments));
			invoke.proceed(arguments);
		}
	}
	
	@Around("execution(* *.setActive(..)) && within(*..service.impl.*)")
	public void beforeSetActive(ProceedingJoinPoint joinPoint) throws Throwable {
		Object arguments[] = joinPoint.getArgs();
		log.debug("Before setActive({}) ", Arrays.toString(arguments));

		String principal = SessionUtils.getPrincipalName();
		if(arguments.length < 3) {
			throw new CommonRuntimeException(RC_INVALID_PARAMETER, "Number of Argument minimum is 3");
		}
		
		if (joinPoint instanceof MethodInvocationProceedingJoinPoint) {
			MethodInvocationProceedingJoinPoint invoke = (MethodInvocationProceedingJoinPoint) joinPoint;
			arguments[2] = principal;
			
			log.debug("Set new Arguments for method setActive({}) ", Arrays.toString(arguments));
			invoke.proceed(arguments);
		}
	}
	
}
