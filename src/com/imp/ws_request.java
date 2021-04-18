package com.imp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JEditorPane;
import javax.swing.JFrame;

import com.util.*;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.apache.log4j.Logger;

import com.pdt.validation;
/**
 * Servlet implementation class ws_request
 */
public class ws_request extends HttpServlet 
{
	private static  Logger request_log = Logger.getLogger("request_log");
	
	String mobile_number 		= 	null;
	String try_count			=   null;
	validation o_val         	= 	new validation();
	
    private static final long serialVersionUID = -1641096228274971485L;
	
    /*http://localhost:8081/Send_sms_customer/sendmessage?mobile_no=8904524449&try_count=3*/
  //editorPane.setPage(new URL("http://smsapp.mx9.in/smpp?username=landmark&password=KiK19IGCq&from=HOMCEN&to="+mobile_number+"&text="+reload_str+""));

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
		//URL hitting method
		 mobile_number = o_val.get_request_param_value( request, response,request_log,config.MOBILE_PARAM_ATTR);
		 try_count = o_val.get_request_param_value( request, response,request_log,config.TRY_COUNT);
		 
		 System.out.println("try_count :"+try_count);
		 System.out.println("mobile no :"+mobile_number);
		 
		if(mobile_number == null || mobile_number == "")
 		{
 			request_log.info("Mobile No is empty !!");
 		}
 		
		else if(try_count == null || try_count == "")
 		{
 			request_log.info("try_count is empty !!");
 		}
		else
		{
		 try
		 {
			 JEditorPane editorPane = new JEditorPane();			
    		try 
			{
    			if((config.try_count_map.containsKey(try_count) ) == true )
    			{
    	 			String message = config.try_count_map.get(try_count);
    	 	 		String reload_str = message.replace(" ", "%20");
    	 	 		request_log.info("Try count is :"+try_count);
    	 	 		editorPane.setPage(new URL("http://52.76.49.233/smpp?username=landmark&password=KiK19IGCq&from=HOMCEN&to="+mobile_number+"&text="+reload_str+""));
    	 	 		request_log.info("Message send to the number based on tries - "+mobile_number);
    	 	 		
    	 	 		response.setStatus(HttpServletResponse.SC_OK);
    	 	 		out.println("Success");
    			}
    	 		else
    	 		{
    	 			String reload_str = config.DEFAULT_MESSAGE.replace(" ", "%20");
    	 			request_log.info("Try count is :"+try_count);
    	 			editorPane.setPage(new URL("http://52.76.49.233/smpp?username=landmark&password=KiK19IGCq&from=HOMCEN&to="+mobile_number+"&text="+reload_str+""));
    	 			request_log.info("try count is not match, default message is - "+mobile_number);
    	 			response.setStatus(HttpServletResponse.SC_OK);
    	 	 		out.println("Success");
    	 		}
    					
			}
    		catch (MalformedURLException e) 
			{
				request_log.error("Exception in message sending :"+e.getMessage());
			}
			catch (IOException e)
			{
				request_log.error("Exception in hitting :"+e.getMessage());
			}        	
        }
        catch (Exception e) 
        {
        	request_log.error("Exception in doGet :"+e);
        }	
	}
	}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	doGet(request, response);
    }
}
