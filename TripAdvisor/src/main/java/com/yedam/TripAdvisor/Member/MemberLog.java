package com.yedam.TripAdvisor.Member;

import java.util.Scanner;

import com.yedam.TripAdvisor.List.AppList;

public class MemberLog {
	private String userId;
	private String password;
	private String userName;
	Scanner sc = new Scanner(System.in);
	AppList applist = new AppList();
	public static MemberVO nowUser = new MemberVO();

	MemberServiceImpl memberservice = new MemberServiceImpl();

	public void NewMember() {
		System.out.println("***************************************************************");
		System.out.println("회원가입을 진행합니다.");
		System.out.println("아이디는 영문과 숫자 10자까지 가능합니다.");
		System.out.println("사용할 아이디를 입력하세요.");
		System.out.print(">>");
		String newid = sc.nextLine();
		// newid가 DB에 있다면 가입 불가능

		System.out.println("비밀번호는 영문과 숫자 20자까지 가능합니다.");
		System.out.println("사용할 비밀번호를 입력하세요.");
		System.out.print(">>");
		String newpw = sc.nextLine();

		System.out.println("닉네임은 영문과 숫자 20자, 한글 6자로 제한됩니다.");
		System.out.println("사용할 닉네임을 입력하세요.");
		System.out.print(">>");
		String newname = sc.nextLine();
		MemberVO sighIn = new MemberVO();
		sighIn.setMemberId(newid);
		sighIn.setMemberPw(newpw);
		sighIn.setMemberName(newname);
		// 닉네임도 중복은 가입불가능
		// DB에 해당 정보 3개 입력
		memberservice.MemberInsert(sighIn);

	}

	public void MemberLogin() {
		System.out.println("***************************************************************");
		System.out.println("아이디와 비밀번호를 입력하세요.");
		System.out.print("ID>>");
		String loginId = sc.nextLine();
		System.out.print("PASSWORD>>");
		String loginPw = sc.nextLine();
		MemberVO check = new MemberVO();
		check.setMemberId(loginId);
		check.setMemberPw(loginPw);
		check.setMemberName(null);

		// 아이디와 비밀번호를 DB와 대조하여 맞으면 로그인 성공
		if (loginId != null && loginPw != null) {
			if (check.getMemberId().equals(memberservice.Login(check).getMemberId())
					&&check.getMemberPw().equals(memberservice.Login(check).getMemberPw())) {
				System.out.println("로그인 성공");
				nowUser = memberservice.Login(check);
				sleepTime(800);
				applist.MainList();
			}else {
				System.out.println("로그인 실패");
			}
		}else {
			System.out.println("ID와 PASSWORD를 모두 입력해 주세요.");
		}

	}
	
	private void sleepTime(int sec) {
		long c = System.currentTimeMillis();
		while (true) {
			if ((System.currentTimeMillis() - c) >= sec) {
				break;
			}
		}
	}

}
