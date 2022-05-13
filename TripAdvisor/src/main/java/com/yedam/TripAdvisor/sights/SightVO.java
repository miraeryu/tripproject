package com.yedam.TripAdvisor.sights;

import lombok.Data;

@Data
public class SightVO {
	private String country;
	private String name;
	private String location;
	private String time;
	private String index;
	private int hit;
	
	
	public String toString() {
		System.out.println("****************************");
		System.out.println("   " + name);
		System.out.println("국가 : " + country + ", 조회수 : " + hit);
		System.out.println("운영시간 : " + time);
		System.out.println(location);
		System.out.println("이 장소에 대해 \n" + index);
		System.out.println("****************************");
		return null;
	}
	
	

}
