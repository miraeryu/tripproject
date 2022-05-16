package com.yedam.TripAdvisor.mypage;

import java.util.Date;

import lombok.Data;

@Data
public class MypageVO {
	private String user;
	private String country;//sight_country
	private String sightName;//sight_name
	private int listNumber;
	@Override
	public String toString() {
		return "MypageVO [user=" + user + ", country=" + country + ", sightName=" + sightName + ", list_number="
				+ listNumber + "]";
	}



}
