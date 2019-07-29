package com.ssafy.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Error Fallback (임시 / 추후 커스터마이징)
 * */

@Controller
public class MyErrorController implements ErrorController  {
 
    @RequestMapping("/error")
    public String handleError() {
        return "forward:/";
    }
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
}