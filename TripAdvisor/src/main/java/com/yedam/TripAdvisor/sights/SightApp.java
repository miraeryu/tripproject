package com.yedam.TripAdvisor.sights;

import java.util.List;
import java.util.Scanner;

import com.yedam.TripAdvisor.List.AppList;

public class SightApp {

	public void sightMain() {
		System.out.println("****************************");
		System.out.println();
		System.out.println("        여 행 지 목 록         ");
		System.out.println();
		System.out.println("****************************");
		System.out.println("국가를 선택해주세요.");
		System.out.println();
		run();

		// 여행지 리스트에 등록되어있는 국가 출력

	}

	public void run() {
		SightSearchService service = new SightSearchServiceImpl();
		List<SightVO> list = service.countryList();
		int pagenum = 1;
		int cnt = 1;
		boolean b = true;

		while (b) {

			page(pagenum);

			System.out.println("****************************");
			System.out.printf("입력>>");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			int[] pagecode = new int[] { pagenum, choice };
			switch (choice) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				selectCountry(pagecode);
				break;
			case 8:
				if (pagenum == 1) {
					selectCountry(pagecode);

				} else if (pagenum > 1) {
					pagenum--;
				}
				break;
			case 9:
				pagenum++;
				break;
			case 0:
				System.out.println("메인 화면으로 돌아갑니다.");
				sleepTime(600);
				b = false;
				AppList main = new AppList();
				main.MainList();
				break;
			}
		}
	}

	private int page(int pagenum) {
		SightSearchService service = new SightSearchServiceImpl();
		List<SightVO> list = service.countryList();
		int pageStart = (7 * (pagenum - 1)) + 1;
		int pageEnd = 7 * pagenum;
		int cnt = 1;
		if (pagenum == 1) {
			for (int i = 0; i < 8; i++) {
				System.out.printf("%d) %s", cnt++, list.get(i).getCountry());
				System.out.println();
			}
			System.out.println("9) 다음으로\n0) 메인화면으로");

		} else if (pagenum > 1) {
			for (int i = 0; i < list.size(); i++) {
				if (i >= pageStart && i <= pageEnd) {
					System.out.printf("%d) %s", cnt++, list.get(i).getCountry());
					System.out.println();

				}
			}
			System.out.println("8) 이전으로\n9) 다음으로\n0) 메인화면으로");
		}

		return 0;
	}

	public int[] selectCountry(int[] pagecodesearch) {
		int page = pagecodesearch[0];
		int line = pagecodesearch[1];
		SightSearchService service = new SightSearchServiceImpl();
		List<SightVO> list = service.countryList();
		String countryName = null;
		int searchCountry = 0;
		if (page == 1) {
			searchCountry = line - 1;
		} else if (page > 1) {
			searchCountry = (7 * (page - 1)) + line;
		}
		for (int i = 0; i < list.size(); i++) {
			if (i == searchCountry) {
				countryName = list.get(i).getCountry();
			}
		}
		move();
		selectCountryList(countryName);

		return null;
	}

	public void selectCountryList(String countryName) {
		SightSearchService service = new SightSearchServiceImpl();
		List<SightVO> sightList = service.selectCountryList(countryName);
		SightWarningService warning = new SightWarningService();
		Scanner sc = new Scanner(System.in);

		int pagenum = 1;

		boolean run = true;
		while (run) {

			System.out.println("============================");
			System.out.println("     " + countryName + "     ");
			System.out.println("============================");

			warning.getWarningInfo(countryName);
			countryPage(pagenum, countryName);

			int cnt = 1;
			int selectInt = 0;

//			if (sightList.size() > 8) {
//				for (int i = 0; i < sightList.size(); i++) {
//					System.out.printf("%d) %s | 조회수 : %s", cnt++, sightList.get(i).getName(),
//							sightList.get(i).getHit());
//					System.out.println();
//				}
//
//			}
//			System.out.printf("9) 다음으로\n0) 메인화면으로\n*) %s게시판으로 이동\n#) 여행경보에 대하여", countryName);
			System.out.println();
			System.out.println("============================");
			System.out.println("자세한 정보를 보고 싶다면 번호를 입력하세요.");
			System.out.print("입력>>");
			String select = sc.nextLine();
			switch (select) {
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
				selectInt = Integer.parseInt(select);
				selectSight(pagenum, selectInt, countryName);
				break;
			case "8":
				if (pagenum == 1) {
					selectInt = Integer.parseInt(select);
					selectSight(pagenum, selectInt, countryName);
					break;
				} else if (pagenum > 1) {
					pagenum--;
					break;
				}
			case "9":
				pagenum++;
				break;
			case "0":
				System.out.println("메인화면으로 이동합니다.");
				move();
				AppList main = new AppList();
				main.MainList();
				break;
			case "*": //게시판 구현 후 작성
				break;
			case "#":
				warningInfomation(countryName);
				break;

			}

		}

	}

	private int countryPage(int pagenum, String countryName) {
		SightSearchService service = new SightSearchServiceImpl();
		List<SightVO> list = service.selectCountryList(countryName);
		int pageStart = (7 * (pagenum - 1)) + 1;
		int pageEnd = 7 * pagenum;
		int cnt = 1;

		if (list.size() < 9) {
			for (int i = 0; i < list.size(); i++) {
				System.out.printf("%d) %s | 조회수 : %s", cnt++, list.get(i).getName(), list.get(i).getHit());
				System.out.println();
			}
			System.out.printf("0) 국가선택으로\n*) %s게시판으로 이동\n#) 여행경보에 대하여", countryName);
		} else if (list.size() > 9) {
			if (pagenum == 1) {
				for (int i = 0; i < 8; i++) {
					System.out.printf("%d) %s | 조회수 : %s", cnt++, list.get(i).getName(), list.get(i).getHit());
					System.out.println();
				}
				System.out.printf("9) 다음으로\n0) 국가선택으로\n*) %s게시판으로 이동\n#) 여행경보에 대하여", countryName);
			} else if (pagenum > 1) {
				for (int i = 0; i < list.size(); i++) {
					if (i >= pageStart && i <= pageEnd) {
						System.out.printf("%d) %s | 조회수 : %s", cnt++, list.get(i).getName(), list.get(i).getHit());
						System.out.println();

					}
				}
				System.out.printf("8) 이전으로\n9) 다음으로\n0) 국가선택으로\n*) %s게시판으로 이동\n#) 여행경보에 대하여", countryName);
			}

		}

		return 0;
	}

	public void selectSight(int page, int line, String countryName) {
		SightSearchService service = new SightSearchServiceImpl();
		List<SightVO> list = service.selectCountryList(countryName);
		int searchSight = 0;
		String sightName = null;
		if (page == 1) {
			searchSight = line - 1;
		} else if (page > 1) {
			searchSight = (7 * (page - 1)) + line;
		}
		for (int i = 0; i < list.size(); i++) {
			if (i == searchSight) {
				sightName = list.get(i).getName();
			}
		}
		move();
		selectSightInformation(sightName);
	}

	public void selectSightInformation(String sightName) {
		SightSearchService service = new SightSearchServiceImpl();
		Scanner sc = new Scanner(System.in);

		service.updateHit(sightName);
		service.readMoreSight(sightName).toString();
		System.out.println("1)목록으로 2)이 장소 저장하기 3)이 장소에 대해 작성하기 4)메인화면으로");
		System.out.print("입력>>");
		int select = sc.nextInt();
		switch (select) {
		case 1:
			System.out.println("목록으로 돌아갑니다.");
			sleepTime(600);
			move();
			break;
		case 2: //마이페이지 저장기능
			break;
		case 3: //장소에 대한 글 작성기능
			break;
		case 4:
			System.out.println("메인화면으로 돌아갑니다.");
			sleepTime(600);
			AppList main = new AppList();
			main.MainList();
			break;
		}

	}

	private void warningInfomation(String countryName) {
		SightWarningService warning = new SightWarningService();
		Scanner sc = new Scanner(System.in);
		System.out.println("============================");
		System.out.println("        여행경보에 대하여        ");
		System.out.println("============================");
		System.out.println("외교부에서는 대한민국 국민에 대한 사건·사고 피해를");
		System.out.println("예방하고 안전한 해외 거주·체류 및 방문을 도모하기 위해");
		System.out.println("‘여행경보제도’를 운영해 오고 있습니다.");
		System.out.println();
		System.out.println("**단계별 여행경보 정보**");
		System.out.println("1단계 여행유의 : 국내 대도시보다 상당히 높은 수준의 위험");
		System.out.println("2단계 여행자제 : 국내 대도시보다 매우 높은 수준의 위험");
		System.out.println("3단계 출국권고 : 국민의 생명과 안전을 위협하는 심각한 수준의 위험");
		System.out.println("4단계 여행금지 : 국민의 생명과 안전을 위협하는 매우 심각한 수준의 위험");
		System.out.println();
		warning.getWarningInfo(countryName);
		System.out.println("자세한 정보는 외교부 홈페이지를 참고하세요.");
		System.out.println("https://www.0404.go.kr/");
		System.out.println();
		System.out.println("============================");
		System.out.println("1.이전화면으로 2.메인화면으로");
		System.out.print("입력>>");
		int select = sc.nextInt();
		if (select == 1) {
			move();
		}else if (select == 2) {
			AppList main = new AppList();
			main.MainList();
			move();
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
