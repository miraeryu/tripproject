package com.yedam.TripAdvisor.sights;

import java.util.Date;

import lombok.Data;

@Data
public class SightWarningInfo {

	// 1/여행유의 2/여행자제 3/출국권고 4/여행금지
	private long currentCount; // 자료가 있는지 없는지 확인
	private int alarm_lvl; // 여행경보단계
	private String region_ty; // 일부 혹은 전체
	private String written_dt; // 경보 발령일

	@Override
	public String toString() {
		int level = alarm_lvl;
		String stringLevel = null;
		switch(level) {
		case 1:
			stringLevel = "여행유의";
			break;
		case 2:
			stringLevel = "여행자제";
			break;
		case 3:
			stringLevel = "여행출국권고";
			break;
		case 4:
			stringLevel = "여행금지";
			break;
		
		}
		System.out.println("**해당 국가는 현재 " + region_ty + "지역에 " + alarm_lvl + "단계 " + stringLevel + "가 발령중입니다.**");
		System.out.println();

		return null;
	}

}
