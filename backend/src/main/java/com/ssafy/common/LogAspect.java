package com.ssafy.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssafy.common.security.SecurityCommon;

/**
 * Logging
 */
@Aspect
@Component
public class LogAspect {
	private static final Logger LOGGER = LogManager.getLogger(LogAspect.class);

	SecurityCommon securityCommon;

	@Autowired
	public LogAspect(SecurityCommon securityCommon) {
		this.securityCommon = securityCommon;
	}

	@Around("within(com.ssafy.rest.*)")
	public Object logging(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		LOGGER.info("[*] START  - [" + pjp.getSignature().getDeclaringTypeName() + "] [USER: "
				+ securityCommon.getUserInfoFromSecurityContextHolder() + "]" + " / [METHOD : " + request.getMethod()
				+ "] " + pjp.getSignature().getName());

		long start = System.currentTimeMillis();
		Object result = pjp.proceed();
		long end = System.currentTimeMillis();
		LOGGER.info("[*] FINISH - [" + pjp.getSignature().getDeclaringTypeName() + "] [USER: "
				+ securityCommon.getUserInfoFromSecurityContextHolder() + "]" + " / [METHOD : " + request.getMethod()
				+ "] " + pjp.getSignature().getName() + " [" + (end - start) + "ms] ");
		return result;
	}
}
