package com.yedam.TripAdvisor.List;

import java.util.InputMismatchException;
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
			System.out.println("***************************************************************");
			System.out.println();
			System.out.println("                    환영합니다. 트립 어드바이저    ");
			System.out.println();
			System.out.println("***************************************************************");
			System.out.println("원하는 메뉴를 선택해주세요.");
			System.out.println("**1.여행지검색");
			System.out.println("**2.커뮤니티");
			System.out.println("**3.마이페이지");
			System.out.println("**4.환율계산기");
			System.out.println("**5.로그아웃");
			System.out.print(">>");
			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();

			} catch (InputMismatchException e) {
				sc.nextLine();
			}
			switch (select) {
			case 1:
				move();
				SightApp sightapp = new SightApp();
				sightapp.sightMain();
				break;
			case 2:
				move();
				CommunityApp communityapp = new CommunityApp();
				communityapp.communityMain();
				break;
			case 3:
				move();
				MypageApp mypageapp = new MypageApp();
				mypageapp.mypageMain();
				break;
			case 4:
				move();
				CurrencyApp currencyapp = new CurrencyApp();
				currencyapp.run();
				break;

			case 5:
				Logout();
				run = false;
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				System.out.println("다시 입력해주세요.");
				load();

			}
		}
	}

	public static void Logout() {
		// 로그아웃하면 로그인화면으로 돌아가거나 시스템이 종료되도록 설정
		System.out.println("시스템 로그아웃 중입니다.");
		System.out.println("다음에 또 만나요!");
		Main main = new Main();
		main.Start();

	}

	public static void sleepTime(int sec) {
		long c = System.currentTimeMillis();
		while (true) {
			if ((System.currentTimeMillis() - c) >= sec) {
				break;
			}
		}
	}

	public static void move() {
		System.out.printf("이동중");
		sleepTime(200);
		System.out.printf(".");
		sleepTime(200);
		System.out.printf(".");
		sleepTime(200);
		System.out.printf(".");
		sleepTime(200);
		System.out.printf(".");
		sleepTime(200);
		System.out.printf(".");
		sleepTime(200);
		System.out.println();

	}

	public static void load() {
		System.out.printf("로딩중");
		sleepTime(200);
		System.out.printf(".");
		sleepTime(200);
		System.out.printf(".");
		sleepTime(200);
		System.out.printf(".");
		sleepTime(200);
		System.out.printf(".");
		sleepTime(200);
		System.out.printf(".");
		sleepTime(200);
		System.out.println();

	}

}
