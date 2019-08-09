package com.ssafy.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ssafy.exception.NoAuthenticationException;

@Component
public class HttpInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if(handler instanceof HandlerMethod) { // OPTHIONS Method가 아니면,
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
			
			if(auth == null) { // Method에 설정된 권한이 없으므로 사용가능
				return true;
			}else{ 			   // Method에 설정된 최소 권한이 있으면 (1:SUPERVISOR, 2:MEMBER, 3:VISITOR),
				int minimumAuth = auth.minimum().getRoleType();  // Method의 최소 권한
				int requestAuth = requestAuthToInteger(request); // 사용자의 권한

				if(requestAuth > minimumAuth) {					 // 권한이 충족되지 않으면,
					throw new NoAuthenticationException(request.getHeader("accountEmail"));
				}
				
				return true;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
	}

	private int requestAuthToInteger(HttpServletRequest request) {
		if (request.getHeader("accountAuth") == null) {
			return Integer.MAX_VALUE;
		} else {
			return Integer.parseInt(request.getHeader("accountAuth"));
		}
	}
}
