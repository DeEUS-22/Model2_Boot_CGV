package com.springboot.mycgv.dto;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MovieDto {

	private int rno;
	private String mcategory; 
	private String mname; 
	private String mdesc; 
	private String mfile1; 
	private String mfile2; 
	private String msfile1; 
	private String msfile2; 
	private String mdate; 
	private String mid;
	private MultipartFile[] files;
	private ArrayList<String> mfiles = new ArrayList<String>();  
	private ArrayList<String> msfiles = new ArrayList<String>();  
	
	
}
