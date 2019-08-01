package com.ssafy.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 권한 체크 후 URI에 대한 접근이 가능한지만 판별하는 Class
 */
@Component
public class HttpInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		if (auth == null) { // 해당 메소드는 권한이 필요 없음
			return true;
		} else { // 권한이 필요한 메소드 (1:SUPERVISOR, 2:MEMBER, 3:VISITOR)
			int minimumAuth = auth.minimum().getRoleType(); // 요청한 Method의 최소 권한
			int requestAuth = requestAuthToInt(request); // 요청하는 사용자의 권한

			if (requestAuth > minimumAuth) { // 권한을 갖지 못하는 경우
				return false;
			}
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
	}

	private int requestAuthToInt(HttpServletRequest request) {
		if (request.getHeader("authentization") == null) {
			return Integer.MAX_VALUE;
		} else {
			return Integer.parseInt(request.getHeader("authentization"));
		}
	}
}
