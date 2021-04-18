package com.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class read_property 
{
	public boolean read_config(String location) throws Exception
	{
		try 
		{
			File 	 			_xmlfile;
			Document 			_doc;	
			DocumentBuilder 	_builder;
			NodeList 			_config_node; 
			NodeList 			_try_count;
			
			DocumentBuilderFactory 		_obj_factory	= 	DocumentBuilderFactory.newInstance();
			_obj_factory.setIgnoringElementContentWhitespace(true);
		
			_xmlfile 		= 	new File(location);
			_builder 	 	=	_obj_factory.newDocumentBuilder();
			_doc 		 	= 	_builder.parse(_xmlfile);
			_config_node	= 	_doc.getElementsByTagName("*");
			_try_count		= 	_doc.getElementsByTagName("Try_count");

			
			get_configurations ( _config_node );	
			try_count_lst(_try_count);
			
			return true;	

		}
		catch (Exception e)
		{
			throw new Exception("Excep while reading property file : " + e.getMessage());
			
		}
	}
	
	private void get_configurations(NodeList _data_list) throws Exception
	{
		config.DEFAULT_MESSAGE			=	get_prop_value ( _data_list , "default_message" , "3" );	
		
	}
	
	private String get_prop_value(NodeList _data_list, String property_name , String def_value ) throws Exception
	{	
		for ( int i=0 ; i<_data_list.getLength() ; i++)  
		{
			if (_data_list.item(i).getNodeName().equalsIgnoreCase(property_name))
			{
				return _data_list.item(i).getTextContent().toString();	
			}
		}
		return def_value ;
	}
	private void try_count_lst(NodeList _childnode ) throws Exception
	{
		for (int temp = 0; temp < _childnode.getLength(); temp++) 
		{
			
			Node nNode = _childnode.item(temp);							
					
			if (nNode.getNodeType() == Node.ELEMENT_NODE) 
			{
				Element eElement = (Element) nNode;	
				
				String count 		= eElement.getAttribute("count");
				String text_message 	= eElement.getAttribute("text_message");
				config.try_count_map.put(count, text_message);
				
			}
		}
	}
}
