//package com.ldce.controller;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//public class WhiteLableErrorController implements ErrorController {
//
//	@RequestMapping("/error")
//	public ModelAndView handleError(HttpServletRequest request) {
//		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//		System.out.println("WhiteLable Error " + status);
//		if (status != null) {
//			Integer statusCode = Integer.valueOf(status.toString());
//
//			if (statusCode == HttpStatus.NOT_FOUND.value()) {
//				return new ModelAndView("404.html");
//			}
//
//			else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//
//				return new ModelAndView("500.html");
//			} else if (statusCode == HttpStatus.FORBIDDEN.value()) {
//
//				return new ModelAndView("403.html");
//			}
//
//		}
//		return new ModelAndView("error.html");
//	}
//
//	@Override
//	public String getErrorPath() {
//		return "/error";
//	}
//}
