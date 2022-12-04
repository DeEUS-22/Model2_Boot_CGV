package com.springboot.mycgv.dto;

import lombok.Data;

@Data
public class MemberDto {

	private int rno;
	private String id;
	private String pass;
	private String name;
	private String gender;
	private String email1;
	private String email2;
	private String zonecode;
	private String addr1;
	private String addr2;
	private String hp;
	private String pnum1;
	private String pnum2;
	private String pnum3;
	private String intro;
	private String mdate;
	private String[] hobby;
	
	private String email;
	private String pnumber;
	private String hobbylist;
	private String address;
	
	
	public String getEmail() {
		return email1 + "@" + email2;
	}
	
	public String getPnumber() {
		if(pnumber == null) {
			return pnum1+"-"+pnum2+"-"+pnum3;
		}else {
			return pnumber; 
		}
	}
	
	public String getHobbylist() {
		if(hobbylist == null) {
			return String.join(",", hobby);
		}else {
			return hobbylist;
		}
	}
	
	public String getAddress() {
		if(address == null) {
			return addr1 + " " + addr2;
			
		}else {
			return address;
		}
	}
	
	
	
}







