package com.yedam.TripAdvisor.mypage;

import java.util.List;

import com.yedam.TripAdvisor.community.CommunityVO;

public interface MypageService {
	
	public List<MypageVO> myTripSightList(String user);
	
	public List<CommunityVO> myBoardList(String writter);

}
