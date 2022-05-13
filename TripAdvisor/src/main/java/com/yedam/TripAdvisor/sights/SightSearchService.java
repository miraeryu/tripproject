package com.yedam.TripAdvisor.sights;

import java.util.List;

public interface SightSearchService {
	
	List <SightVO> selectAllList(); //전체관광지 조회
	List <SightVO> countryList(); //국가목록출력
	List <SightVO> selectCountryList(String country); //한 국가 선택 조회 =>조회수기준 출력
	SightVO readMoreSight(String name); //단건 상세보기
	void updateHit(String name); //조회수 업데이트
	void myListSave(); //내 리스트에 저장
	

}
