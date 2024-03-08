package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class FrontController {
	 @RequestMapping("/registersuccess")
	    public String showRegisterSuccessPage() {
	        return "registersuccess";
	    }
	 
	 @RequestMapping("/loginpage")
	    public String showLoginPage() {
	        return "login";
	    }
	 
	 @RequestMapping("/")
	    public String showmainPage() {
	        return "index";
	    }
	 
	 @RequestMapping("/ciao")
	 public String showprotectedpage() {
		 return "ciao";
	 }

	
}
