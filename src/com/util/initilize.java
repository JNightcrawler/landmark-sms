package com.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

@WebListener
public class initilize implements ServletContextListener 
{
	private static  Logger log = Logger.getLogger("initilize_log");
	
	static String app_version ="1.1";

	@Override
	public void contextInitialized(ServletContextEvent sce) 
	{
		on_start(sce.getServletContext().getRealPath("/") );
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) 
	{
		
	}
	private void on_start( String app_path )
	{	
		 try 
		 {
			 read_from_property(app_path );
		 } 
		 catch (Exception e) 
		 {
			e.printStackTrace();
			log.fatal( "property reading failed : " + e.getMessage() );
			
		 }
		
	}
	
	void read_from_property(String app_path)
	{
		
		String prop_file_path =  app_path + "/WEB-INF/properties.xml";
		log.info("App path : " + app_path);
		log.info("Property path : " + prop_file_path);
		read_property prop = new read_property( );

		try 
		{
			prop.read_config(prop_file_path );		
		} 
		catch (Exception e) 
		{
			log.fatal( "Exception to read property file : " + e.getMessage() );
		}		
	}
}
