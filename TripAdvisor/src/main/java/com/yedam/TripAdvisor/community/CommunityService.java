package com.yedam.TripAdvisor.community;

import java.util.List;

public interface CommunityService {
	
	List<CommunityVO> selectAllList();//전체글보기
	
	List <CommunityVO> countryList();//국가 출력
	
	List <CommunityVO> selectCountryList(String country);//국가별 게시판 보기
	
	void addBoard();//글 등록
	
	void modify(CommunityVO vo);//글 수정
	
	void delete(int number);//글 삭제
	
	CommunityVO readmore(int number);//글 1건 보기

}
