package com.yedam.TripAdvisor.community;

public class CommunityApp {
	
	
	public void communityMain() {
		System.out.println("****************************");
		System.out.println();
		System.out.println("          커 뮤 니 티          ");
		System.out.println();
		System.out.println("****************************");
		System.out.println("메뉴를 선택하세요.");
		run();

	}
	
	 public void run() {

		 boolean run = true;
		 
		 while(run) {
				//1. 전체보기 2.국가별로보기
				System.out.println("1.전체글\n2.국가별 커뮤니티\n3.메인화면으로");
				System.out.println("****************************");
				System.out.println("입력>>");
				Scanner sc = new Scanner(System.in);
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					System.out.println("메인 화면으로 돌아갑니다.");
					sleepTime(600);
					move();
					boolean run = false;
					break;
					
				
				}
				
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

		private void move() {
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

	}


}
