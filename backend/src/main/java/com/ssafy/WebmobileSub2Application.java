package com.ssafy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.ssafy.respository.PostsRespository;
import com.ssafy.vo.Posts;

@EnableAspectJAutoProxy
@SpringBootApplication
public class WebmobileSub2Application {

	public static void main(String[] args) {
		SpringApplication.run(WebmobileSub2Application.class, args);
	}
	
}