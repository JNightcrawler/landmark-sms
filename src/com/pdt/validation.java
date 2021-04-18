package com.pdt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class validation 
{
	String mobile_Tries = null;
	
	public String get_request_param_value(HttpServletRequest request, HttpServletResponse response,Logger request_log,String paramNa)
	{
		String paramName = paramNa;
		 mobile_Tries = request.getParameter(paramName);
         String url = request.getRequestURL().toString();
        
         request_log.info("Request URL :" +url);
	     request_log.info("Request Mobile Number or try count :"+mobile_Tries);
         
         return mobile_Tries.trim();
	}
	
}
