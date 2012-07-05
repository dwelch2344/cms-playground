package com.example.UploadPlugin.model;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

	private MultipartFile file;
	private String classname;
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	
	
}
