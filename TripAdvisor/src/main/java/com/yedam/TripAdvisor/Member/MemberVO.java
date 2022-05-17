package com.yedam.TripAdvisor.Member;

import lombok.Data;

@Data
public class MemberVO {
	private String memberId;
	private String memberPw;
	private String memberName;
	
	
	
	@Override
	public String toString() {
		System.out.println("***************************************************************");
		System.out.println();
		System.out.println("                          내  정  보           ");
		System.out.println();
		System.out.println("회원 아이디 : " + memberId);
		System.out.println("회원 비밀번호 : 암호화 되어있음");
		System.out.println("회원 닉네임 : " + memberName);
		System.out.println();
		System.out.println("***************************************************************");

		return null;
	}
	
	

}
