package com.example.rentavehicleagency.services;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		var authourities = authentication.getAuthorities();
		var roles = authourities.stream().map(r->r.getAuthority()).findFirst();
		
		if(roles.orElse("").equals("OWNER")) {
			response.sendRedirect("/owner-page");
		}else if(roles.orElse("").equals("DIRECTOR")) {
			response.sendRedirect("/director-page");
		}else if(roles.orElse("").equals("HREMPLOYEE")) {
			response.sendRedirect("/hr-page");
		}else if(roles.orElse("").equals("FINEMPLOYEE")) {
			response.sendRedirect("/finance-page");
		}else if(roles.orElse("").equals("RENTEMPLOYEE")) {
			response.sendRedirect("/rent-dashboard-page");
		}else if(roles.orElse("").equals("MAINTEMPLOYEE")) {
			response.sendRedirect("/maintenance-dashboard-page");
		}else if(roles.orElse("").equals("USER")) {
			response.sendRedirect("/user-page");
		}else {
			response.sendRedirect("/error");
		}
		
	}

}
