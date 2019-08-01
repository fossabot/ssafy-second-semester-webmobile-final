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

/**
 * Logging
 */
@Aspect
@Component
public class LogAspect {
	private static final Logger LOGGER = LogManager.getLogger(LogAspect.class);

	@Around("within(com.ssafy.api.*)")
	public Object logging(ProceedingJoinPoint pjp) throws Throwable {
		LOGGER.info(logToString(pjp,"START"));
		Object result = pjp.proceed();
		LOGGER.info(logToString(pjp,"END"));
		return result;
	}
	
	/**
	 * 로그 찍는 구문 문자열 Build
	 * */
	private String logToString(ProceedingJoinPoint pjp, String MethodType) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();

		String userInfo = "USER: ";

		if (request.getHeaderNames() == null) {
			userInfo += "ANONYMOUS";
		} else {
			userInfo +=request.getHeader("email") + ", AUTH: " + request.getHeader("authentization");
		}

		StringBuilder sb = new StringBuilder();

		sb
		.append("[*] [")
		.append(MethodType)
		.append("] - [")
		.append(pjp.getSignature().getDeclaringTypeName())
		.append("] [")
		.append(userInfo)
		.append("] / [METHOD: ")
		.append(request.getMethod())
		.append("] ")
		.append(pjp.getSignature().getName()).append("\n");
		
		return sb.toString();
	}
}
