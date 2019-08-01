package com.ssafy.cofig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	@Qualifier(value = "httpInterceptor")
	private HandlerInterceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 모든 URI 접근에 대해 권한을 확인함
		registry.addInterceptor(interceptor).addPathPatterns("/**");
	}
	
	
//	@Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry
//        .addMapping("/*")
//        .allowedOrigins("*")
//        .allowedMethods("GET", "POST", "OPTIONS", "PUT","DELETE")
//        .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
//                        "Access-Control-Request-Headers").allowedOrigins("http://70.12.246.33:8080")
//        .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
//        .allowCredentials(true).maxAge(3600);
//    }
}
