package com.yedam.TripAdvisor.List;


import java.util.Scanner;

import com.yedam.TripAdvisor.Main;
import com.yedam.TripAdvisor.Currency.CurrencyApp;
import com.yedam.TripAdvisor.Member.MemberVO;
import com.yedam.TripAdvisor.community.CommunityApp;
import com.yedam.TripAdvisor.mypage.MypageApp;
import com.yedam.TripAdvisor.sights.SightApp;

public class AppList {
	

	
	// 메뉴 출력 1.~5.
	public static void MainList() {
		Main main = new Main();
		boolean run = true;
		while (run) {
			System.out.println("****************************");
			System.out.println();
			System.out.println("    환영합니다. 트립 어드바이저    ");
			System.out.println();
			System.out.println("****************************");
			System.out.println("원하는 메뉴를 선택해주세요.");
			System.out.println("**1.여행지검색");
			System.out.println("**2.커뮤니티");
			System.out.println("**3.마이페이지");
			System.out.println("**4.환율계산기");
			System.out.println("**5.로그아웃");
			System.out.print(">>");
			Scanner sc = new Scanner(System.in);
			int menuselect = sc.nextInt();
			switch (menuselect) {
			case 1:
				SightApp sightapp = new SightApp();
				sightapp.sightMain();

			case 2:
				CommunityApp communityapp = new CommunityApp();
				communityapp.communityMain();

			case 3:
				MypageApp mypageapp = new MypageApp();
				mypageapp.mypageMain();

			case 4:
				CurrencyApp CRUN = new CurrencyApp();
				CRUN.Crun();
				break;

			case 5:
				Logout();
				run = false;
				break;
			}
		}
	}




	public static void Logout() {
		// 로그아웃하면 로그인화면으로 돌아가거나 시스템이 종료되도록 설정
		System.out.println("시스템 로그아웃 중입니다.");
		System.out.println("다음에 또 만나요!");

	}
}
