package com.yedam.TripAdvisor.Member;

public interface MemberService {
	
	//회원가입
	int MemberInsert(MemberVO vo);
	
	//로그인
	MemberVO Login(MemberVO vo);
	

}
