package com.yedam.TripAdvisor;

import java.util.Scanner;

import com.yedam.TripAdvisor.Currency.CurrencyApp;
import com.yedam.TripAdvisor.Member.MemberLog;

public class Main {

	Scanner sc = new Scanner(System.in);
	MemberLog memberlog = new MemberLog();
	boolean b = true;

	// 로그인 로그아웃 회원가입
	public void Start() {
		while (b) {

			System.out.println("****************************");
			System.out.println();
			System.out.println("   T R I P  A D V I S O R   ");
			System.out.println();
			System.out.println("****************************");
			System.out.println("이용하시려면 로그인이 필요합니다.");
			System.out.println("아이디가 없을 경우 회원가입을 해주세요.");
			System.out.println("1.로그인 2.회원가입 3.프로그램 종료");
			System.out.print(">>");
			int loginSelect = sc.nextInt();

			if (loginSelect == 1) {
				memberlog.MemberLogin();
			} else if (loginSelect == 2) {
				memberlog.NewMember();
			} else if (loginSelect == 3) {
				System.out.println("프로그램을 종료합니다.");
				b = false;
				break;
			}
			
			else {// 혹은 try catch
				System.out.println("잘못된 입력입니다.");

			}
		}
	}

}
