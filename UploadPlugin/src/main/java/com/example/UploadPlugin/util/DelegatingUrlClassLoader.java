package com.example.UploadPlugin.util;

import java.net.URL;
import java.net.URLClassLoader;

import org.apache.log4j.Logger;

public class DelegatingUrlClassLoader extends URLClassLoader{

	private final Logger logger = Logger.getLogger(getClass());
	
	public DelegatingUrlClassLoader(URL... urls){
		super(urls);
	}

	@Override
	protected Class<?> findClass(String name)
			throws ClassNotFoundException {
		try{
			return super.findClass(name);
		}catch(Exception e){
			//logger.warn("Failed loading class " + name, e);
			logger.warn("Failed loading class " + name);
		}
		
		return Class.forName(name);
	}
}
