package com.sulistionoadi.ngoprek.common.utils;

import java.beans.Expression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

//import com.sulistionoadi.ngoprek.common.dto.security.UserLogin;

public class SessionUtils {
	
	private static final Logger log = LoggerFactory.getLogger(SessionUtils.class);

	public static String getPrincipalName() {
		String principal = "Anonymous";
		
		Object sessionLogin = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(sessionLogin!=null) {
			String sessionClass = sessionLogin.getClass().getSimpleName();
			log.debug("Instance of Session Login {}", sessionClass);
			
			if(sessionLogin instanceof String) {
				principal = sessionLogin.toString();
			//} else if(sessionLogin instanceof UserLogin) {
			//	principal = ((UserLogin) sessionLogin).getUsername();
			} else {
				try {
					Expression expr = new Expression(sessionLogin, "getUsername", new Object[0]);
					principal = (String) expr.getValue();
				} catch (Exception e) {
					log.warn("Cannot getUsername from SessionLogin with class:{}, Cause:{}", 
							sessionClass, e.getMessage(), e);
				}
			}
		}
		return principal;
	}
	
}
