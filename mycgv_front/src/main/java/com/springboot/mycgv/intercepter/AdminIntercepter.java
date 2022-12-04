package com.springboot.mycgv.intercepter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.springboot.mycgv.dto.SessionDto;

public class AdminIntercepter implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		
		SessionDto svo = (SessionDto) session.getAttribute("svo");
		if(svo == null) {
			response.sendRedirect("http://localhost:9004/login");
			return false;
		}else {
			if(!svo.getId().equals("admin")) {
				response.sendRedirect("http://localhost:9004/access_reject");
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('해당 페이지 접근 권한이 없습니다.')</script>");
				out.flush();
				
				return false;
			}
		}

		return true;
	}
}
