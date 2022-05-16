package com.yedam.TripAdvisor.community;

import java.sql.Date;

import lombok.Data;

@Data
public class CommunityVO {
	private int number;
	private String subject;
	private String country;
	private String location;
	private String index;
	private String writter;
	private Date writtenDate;
	

	@Override
	public String toString() {
		System.out.println("***************************************************************");
		System.out.println();
		System.out.println("번호 : "+ number +"  제목 : " + subject);
		System.out.println("작성자 : " + writter +"  작성일 : " +writtenDate 
				+ "\n국가 : " + country + "  장소 : " + location 
				+ "\n내용 : " + index);
		System.out.println();
		System.out.println("***************************************************************");

		return null;
	}
}
