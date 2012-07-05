package com.example.UploadPlugin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import co.ntier.cms.PluginDescriptor;

import com.example.UploadPlugin.model.FileUpload;
import com.example.UploadPlugin.util.DelegatingUrlClassLoader;

@Controller
public class HomeController {

	@Inject
	private AnnotationConfigWebApplicationContext ctx;
	
	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public void upload(FileUpload upload, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("Got upload! ");
		
		File file = File.createTempFile("somethin", ".jar");
		file.deleteOnExit();
		System.out.println("Created " + file);
		
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(upload.getFile().getBytes());
		fos.close();
		
		
		
		String path = file.getAbsolutePath();
		String urlPath = "jar:file://" + path + "!/";
		System.out.println("Loading from " + path + " as " + urlPath);
		
		final String classname = upload.getClassname();
		System.out.println("Loading class " + classname);
		
		DelegatingUrlClassLoader loader = new DelegatingUrlClassLoader( new URL(urlPath) );
		
		Class<?> klass = loader.loadClass(classname);
		System.out.println("Loaded " + klass);
		
		Object o = klass.newInstance();
		System.out.println("Got object: " + o);
		
		if( o instanceof PluginDescriptor ){
			System.out.println("Object is PluginDescriptor!");
			PluginDescriptor r = (PluginDescriptor) o;
			System.out.println( String.format("Loaded plugin '%s' (%s)", r.getName(), r.getIdentifier()));
			r.writeData( response.getWriter() );
			response.flushBuffer();
			response.setStatus(HttpServletResponse.SC_OK);
		}else{
			response.getWriter().write("Didn't load a PluginDescriptor");
			response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
			response.flushBuffer();
		}
	}
	
}
