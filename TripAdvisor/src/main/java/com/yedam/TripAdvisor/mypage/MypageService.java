package com.yedam.TripAdvisor.mypage;

import java.util.List;

import com.yedam.TripAdvisor.community.CommunityVO;

public interface MypageService {
	
	public List<MypageVO> myTripSightList(String user);
	
	public void addList(MypageVO vo);
	
	public void delateList(int number);
	
	public List<CommunityVO> myBoardList(String writter);

}
