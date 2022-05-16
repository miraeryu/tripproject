package com.yedam.TripAdvisor.sights;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.yedam.TripAdvisor.List.AppList;
import com.yedam.TripAdvisor.Member.MemberLog;
import com.yedam.TripAdvisor.community.CommunityApp;
import com.yedam.TripAdvisor.community.CommunityService;
import com.yedam.TripAdvisor.community.CommunityServiceImpl;
import com.yedam.TripAdvisor.community.CommunityVO;
import com.yedam.TripAdvisor.mypage.MypageService;
import com.yedam.TripAdvisor.mypage.MypageServiceImpl;
import com.yedam.TripAdvisor.mypage.MypageVO;

public class SightApp {
	AppList main = new AppList();

	public void sightMain() {
		SightSearchService service = new SightSearchServiceImpl();
		List<SightVO> list = service.countryList();
		int pagenum = 1;
		int cnt = 1;
		boolean run = true;

		while (run) {
			System.out.println("===============================================================");
			System.out.println();
			System.out.println("                    여  행  지  목  록         ");
			System.out.println();
			System.out.println("===============================================================");
			System.out.println();
			page(pagenum);
			System.out.println("                        ==" + pagenum + "페이지==");
			System.out.println("===============================================================");
			System.out.println("국가를 선택해주세요.");
			System.out.printf(">>");
			Scanner sc = new Scanner(System.in);
			int choice = 0;
			try {
				choice = sc.nextInt();
			} catch (InputMismatchException e) {
				sc.nextLine();
			}
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
				main.move();
				run = false;
				main.MainList();
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				System.out.println("다시 입력해주세요.");
				main.load();
			}
		}
	}

	private int page(int pagenum) {
		SightSearchService service = new SightSearchServiceImpl();
		List<SightVO> list = service.countryList();
		int pageStart = (7 * (pagenum - 1)) + 1;
		int pageEnd = 7 * pagenum;
		int cnt = 1;
		if (pageStart <= list.size()) {

			if (pagenum == 1) {
				for (int i = 0; i < 8; i++) {
					System.out.printf("%d) %s", cnt++, list.get(i).getCountry());
					System.out.println();
				}
				System.out.println();
				System.out.println("9) 다음으로\n0) 메인화면으로");

			} else if (pagenum > 1) {
				for (int i = 0; i < list.size(); i++) {
					if (i >= pageStart && i <= pageEnd) {
						System.out.printf("%d) %s", cnt++, list.get(i).getCountry());
						System.out.println();

					}
				}
				System.out.println();
				System.out.println("8) 이전으로\n9) 다음으로\n0) 메인화면으로");
			}
		} else {
			System.out.println("더 이상 페이지가 없습니다.");
			System.out.println("0을 눌러 메인화면으로 이동하세요.");
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
		main.move();
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

			System.out.println("===============================================================");
			System.out.println();
			System.out.println("                           " + countryName);
			System.out.println();
			System.out.println("===============================================================");

			warning.getWarningInfo(countryName);
			countryPage(pagenum, countryName);

			int cnt = 1;
			int selectInt = 0;

			System.out.println();
			System.out.println("                        ==" + pagenum + "페이지==");
			System.out.println("===============================================================");
			System.out.println("자세한 정보를 보고 싶다면 번호를 입력하세요.");
			System.out.print(">>");
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
				main.move();
				selectSight(pagenum, selectInt, countryName);
				break;
			case "8":
				if (pagenum == 1) {
					selectInt = Integer.parseInt(select);
					main.move();
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
				System.out.println("국가선택화면으로 이동합니다.");
				main.move();
				run = false;
				break;
			case "*": // 게시판 구현 후 작성
				main.move();
				moveBoard(countryName);
				break;
			case "#":
				main.move();
				warningInfomation(countryName);
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				System.out.println("다시 입력해주세요.");
				main.load();

			}

		}

	}

	private int countryPage(int pagenum, String countryName) {
		SightSearchService service = new SightSearchServiceImpl();
		List<SightVO> list = service.selectCountryList(countryName);
		int pageStart = (7 * (pagenum - 1)) + 1;
		int pageEnd = 7 * pagenum;
		int cnt = 1;
		if (pageStart <= list.size()) {

			if (list.size() < 9) {
				for (int i = 0; i < list.size(); i++) {
					System.out.printf("%d) %s | 조회수 : %s", cnt++, list.get(i).getName(), list.get(i).getHit());
					System.out.println();
				}
				System.out.println();
				System.out.printf("0) 국가선택으로\n*) %s게시판으로 이동\n#) 여행경보에 대하여", countryName);
			} else if (list.size() > 9) {
				if (pagenum == 1) {
					for (int i = 0; i < 8; i++) {
						System.out.printf("%d) %s | 조회수 : %s", cnt++, list.get(i).getName(), list.get(i).getHit());
						System.out.println();
					}
					System.out.println();
					System.out.printf("9) 다음으로\n0) 국가선택으로\n*) %s게시판으로 이동\n#) 여행경보에 대하여", countryName);
				} else if (pagenum > 1) {
					for (int i = 0; i < list.size(); i++) {
						if (i >= pageStart && i <= pageEnd) {
							System.out.printf("%d) %s | 조회수 : %s", cnt++, list.get(i).getName(), list.get(i).getHit());
							System.out.println();

						}
					}
					System.out.println();
					System.out.printf("8) 이전으로\n9) 다음으로\n0) 국가선택으로\n*) %s게시판으로 이동\n#) 여행경보에 대하여", countryName);
				}

			}
		} else {
			System.out.println("더 이상 페이지가 없습니다.");
			System.out.println("0을 눌러 국가선택화면으로 이동하세요.");
		}

		return 0;
	}

	private void moveBoard(String countryName) {
		CommunityApp communityapp = new CommunityApp();
		communityapp.countryPage(countryName);

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
		main.move();
		selectSightInformation(sightName);
	}

	public void selectSightInformation(String sightName) {
		SightSearchService service = new SightSearchServiceImpl();
		MypageService mypageService = new MypageServiceImpl();
		Scanner sc = new Scanner(System.in);

		service.updateHit(sightName);
		service.readMoreSight(sightName).toString();
		SightVO sightvo = service.readMoreSight(sightName);
		System.out.println("1)목록으로 2)이 장소 저장하기 3)이 장소에 대해 작성하기 4)메인화면으로");
		System.out.print("입력>>");
		int select = sc.nextInt();
		switch (select) {
		case 1:
			System.out.println("목록으로 돌아갑니다.");
			main.sleepTime(600);
			main.move();
			break;
		case 2: // 마이페이지 저장기능
			MypageVO vo = new MypageVO();
			vo.setCountry(sightvo.getCountry());
			vo.setSightName(sightvo.getName());
			mypageService.addList(vo);
			System.out.println("목록으로 돌아갑니다.");
			main.sleepTime(600);
			main.move();
			break;
		case 3:
			addBoard(sightvo.getCountry(), sightvo.getName());
			break;
		case 4:
			System.out.println("메인화면으로 돌아갑니다.");
			main.sleepTime(600);
			main.move();
			AppList main = new AppList();
			main.MainList();
			break;
		}

	}

	private void addBoard(String countryName, String sightName) {
		CommunityService service = new CommunityServiceImpl();
		Scanner sc = new Scanner(System.in);
		CommunityVO vo = new CommunityVO();
		System.out.println();
		System.out.println("***************************************************************");
		System.out.println();
		System.out.println("                        새  글  작  성");
		System.out.println();
		System.out.println("***************************************************************");
		System.out.print("제목>>");
		String newSubject = sc.nextLine();
		vo.setSubject(newSubject);
		System.out.print("내용>>");
		String newIndex = sc.nextLine();
		vo.setIndex(newIndex);
		vo.setCountry(countryName);
		vo.setLocation(sightName);
		vo.setWritter(MemberLog.nowUser.getMemberName());
		service.addBoard(vo);
		System.out.println("목록으로 돌아갑니다.");
		main.move();
	}

	private void warningInfomation(String countryName) {
		SightWarningService warning = new SightWarningService();
		Scanner sc = new Scanner(System.in);
		System.out.println("===============================================================");
		System.out.println("                   여 행 경 보 에  대 하 여        ");
		System.out.println("===============================================================");
		System.out.println("외교부에서는 대한민국 국민에 대한 사건·사고 피해를 예방하고 안전한");
		System.out.println("해외 거주·체류 및 방문을 도모하기 위해 ‘여행경보제도’를 운영해 오고 있습니다.");
		System.out.println();
		System.out.println("**단계별 여행경보 정보**");
		System.out.println("1단계 여행유의 : 국내 대도시보다 상당히 높은 수준의 위험");
		System.out.println("2단계 여행자제 : 국내 대도시보다 매우 높은 수준의 위험");
		System.out.println("3단계 출국권고 : 국민의 생명과 안전을 위협하는 심각한 수준의 위험");
		System.out.println("4단계 여행금지 : 국민의 생명과 안전을 위협하는 매우 심각한 수준의 위험");
		System.out.println();
		System.out.print("⁂");
		warning.getWarningInfo(countryName);
		System.out.println("자세한 정보는 외교부 홈페이지를 참고하세요.");
		System.out.println("https://www.0404.go.kr/");
		System.out.println();
		System.out.println("===============================================================");
		System.out.println("1.이전화면으로 2.메인화면으로");
		System.out.print(">>");
		int select = sc.nextInt();
		if (select == 1) {
			main.move();
		} else if (select == 2) {
			AppList main = new AppList();
			main.MainList();
			main.move();
		}
	}

}
