package com.ssafy.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LogAspect {
	private static final Logger LOGGER = LogManager.getLogger(LogAspect.class);

	@Around("within(com.ssafy.api.*)")
	public Object logging(ProceedingJoinPoint pjp) throws Throwable {
		LOGGER.info(buildString(pjp,"START"));
		Object result = pjp.proceed();
		LOGGER.info(buildString(pjp," END "));
		return result;
	}

	private String buildString(ProceedingJoinPoint pjp, String logPrefix) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes())
				.getRequest();

		String accountInfo = "";

		if (request.getHeaderNames() == null ||
			request.getHeader("accountEmail") == null ||
			request.getHeader("accountAuth") == null ||
			request.getHeader("accountEmail").equals("") ||
			request.getHeader("accountAuth").equals("")) {
			accountInfo += "********* ANONYMOUS ********";
		} else {
			accountInfo += request.getHeader("accountEmail") + " | AUTH : " + request.getHeader("accountAuth");
		}

		StringBuilder logMessage = new StringBuilder();

		logMessage
		.append("[#] [").append(logPrefix).append("]")
		.append(" - [ACCOUNT : ").append(accountInfo).append("]")
		.append(" / [LOCATION : ").append(pjp.getSignature().getDeclaringTypeName())
		.append(" | [METHOD (").append(request.getMethod()).append(") : ")
		.append(pjp.getSignature().getName()).append("()")
		.append("]");
		
		return logMessage.toString();
	}
	
}
