package com.ssafy.common.security;

import java.util.HashMap;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Spring Security에 저장된 Username을 가져옴
 */
@Component
public class SecurityCommon {
	
	public Object getUserInfoFromSecurityContextHolder() {
		HashMap<String, Object> map = new HashMap<String, Object>();

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			Object o = ((UserDetails) principal).getAuthorities();
			map.put(username, o);
			return map;
		} else {
			String username = principal.toString();
			return username;
		}
	}
}
