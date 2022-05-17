package com.yedam.TripAdvisor.mypage;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.yedam.TripAdvisor.Currency.CurrencyDataImpl;
import com.yedam.TripAdvisor.Currency.CurrencyDataVO;
import com.yedam.TripAdvisor.List.AppList;
import com.yedam.TripAdvisor.Member.MemberLog;
import com.yedam.TripAdvisor.Member.MemberServiceImpl;
import com.yedam.TripAdvisor.Member.MemberVO;
import com.yedam.TripAdvisor.community.CommunityApp;
import com.yedam.TripAdvisor.community.CommunityService;
import com.yedam.TripAdvisor.community.CommunityServiceImpl;
import com.yedam.TripAdvisor.community.CommunityVO;
import com.yedam.TripAdvisor.sights.SightSearchService;
import com.yedam.TripAdvisor.sights.SightSearchServiceImpl;
import com.yedam.TripAdvisor.sights.SightVO;

public class MypageApp {
	AppList main = new AppList();

	public void mypageMain() {
		boolean run = true;
		while (run) {
			System.out.println("***************************************************************");
			System.out.println();
			System.out.println("                      마  이  페  이  지         ");
			System.out.println();
			System.out.println("***************************************************************");
			System.out.println();
			System.out.println("1.내정보\n2.관심여행지\n3.내게시글\n4.돌아가기");
			System.out.println();
			System.out.println("***************************************************************");
			System.out.print("입력>>");
			Scanner sc = new Scanner(System.in);
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (InputMismatchException e) {
				sc.nextLine();
			}
			System.out.println();

			switch (select) {
			case 1:
				main.load();
				loginMemberInfo();
				main.sleepTime(400);
				break;

			case 2:
				main.move();
				mySight();
				break;

			case 3:
				main.move();
				myBoard();
				break;
			case 4:
				System.out.println("메뉴로 돌아갑니다.");
				run = false;
				main.move();
				main.sleepTime(400);
				main.MainList();
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				System.out.println("다시 입력해주세요.");
				main.load();
			}
		}
	}

	private void loginMemberInfo() {
		MemberLog.nowUser.toString();
		System.out.println("1.이전화면으로 2.메인화면으로");
		System.out.print("입력>>");
		Scanner sc = new Scanner(System.in);
		int select = sc.nextInt();
		if (select == 1) {
			System.out.println("이전화면으로 돌아갑니다.");
			main.move();
		} else if (select == 2) {
			System.out.println("메인화면으로 돌아갑니다.");
			main.move();
		} else {
			System.out.println("잘못된 선택입니다. 이전화면으로 돌아갑니다.");
			main.move();
		}
	}

	private void mySight() {
		MypageService service = new MypageServiceImpl();
		List<MypageVO> list = service.myTripSightList(MemberLog.nowUser.getMemberName());
		int pagenum = 1;
		boolean run = true;
		Scanner sc = new Scanner(System.in);
		while (run) {
			System.out.println("***************************************************************");
			System.out.println();
			System.out.println("                  나  의   관  심  여  행  지       ");
			System.out.println();
			System.out.println("***************************************************************");
			System.out.println();
			mySightList(pagenum);
			System.out.println();
			System.out.println("                        **" + pagenum + "페이지**");
			System.out.println();
			System.out.println("***************************************************************");
			System.out.println("자세히 보려면 번호를 입력하세요.");
			System.out.print(">>");
			int select = sc.nextInt();
			switch (select) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				readmoreSight(pagenum, select);
				break;
			case 8:
				if (pagenum == 1) {
					main.move();
					readmoreSight(pagenum, select);
					break;
				} else if (pagenum > 1) {
					pagenum--;
					break;
				}
			case 9:
				pagenum++;
				break;
			case 0:
				System.out.println("이전 화면으로 돌아갑니다.");
				main.move();
				run = false;
				break;
			}
		}

	}

	private void mySightList(int pagenum) {
		MypageService service = new MypageServiceImpl();
		List<MypageVO> list = service.myTripSightList(MemberLog.nowUser.getMemberName());
		int pageStart = (7 * (pagenum - 1)) + 1;
		int pageEnd = 7 * pagenum;
		int cnt = 1;
		if (pageStart <= list.size()) {
			if (list.size() < 9) {
				for (int i = 0; i < list.size(); i++) {
					System.out.printf("%d) %s %s", cnt++, list.get(i).getCountry(), list.get(i).getSightName());
					System.out.println();
				}
				System.out.println();
				System.out.printf("0) 이전메뉴로");
			} else if (list.size() >= 9) {
				if (pagenum == 1) {
					for (int i = 0; i < 8; i++) {
						System.out.printf("%d) %s %s", cnt++, list.get(i).getCountry(), list.get(i).getSightName());
						System.out.println();
					}
					System.out.println();
					System.out.printf("9) 다음으로\n0) 이전메뉴로");
				} else if (pagenum > 1) {
					for (int i = 0; i < list.size(); i++) {
						if (i >= pageStart && i <= pageEnd) {
							System.out.printf("%d) %s %s", cnt++, list.get(i).getCountry(), list.get(i).getSightName());
							System.out.println();

						}
					}
					System.out.println();
					System.out.printf("8) 이전으로\n9) 다음으로\n0) 이전메뉴로");
				}

			}
		} else {
			System.out.println("더 이상 페이지가 없습니다.");
			System.out.println("0을 눌러 이전화면으로 이동하세요.");
		}

	}

	private void readmoreSight(int page, int line) {
		SightSearchService sightService = new SightSearchServiceImpl();
		MypageService mypageService = new MypageServiceImpl();
		List<MypageVO> list = mypageService.myTripSightList(MemberLog.nowUser.getMemberName());
		SightVO vo = new SightVO();
		Scanner sc = new Scanner(System.in);
		int searchBoard = 0;
		if (page == 1) {
			searchBoard = line - 1;
		} else if (page > 1) {
			searchBoard = (7 * (page - 1)) + line;
		}
		for (int i = 0; i < list.size(); i++) {
			if (i == searchBoard) {
				vo.setName(list.get(i).getSightName());
			}
		}
		SightVO result = sightService.readMoreSight(vo.getName());
		result.toString();
		System.out.println("1.삭제하기 2.목록으로 3.메인메뉴로");
		System.out.print("입력>>");
		int select = sc.nextInt();
		switch (select) {
		case 1:
			main.move();
			deleteSight(list.get(searchBoard).getListNumber());
			break;
		case 2:
			System.out.println("목록으로 돌아갑니다.");
			main.sleepTime(400);
			main.move();
			break;
		case 3:
			System.out.println("메인화면으로 돌아갑니다.");
			main.move();
			main.MainList();
			break;
		}
	}

	private void deleteSight(int number) {
		MypageService service = new MypageServiceImpl();
		System.out.println("목록에서 삭제합니다.");
		System.out.printf("삭제 진행중입니다.");
		main.sleepTime(200);
		System.out.printf(".");
		main.sleepTime(200);
		System.out.printf(".");
		main.sleepTime(200);
		System.out.printf(".");
		main.sleepTime(200);
		System.out.printf(".");
		main.sleepTime(200);
		System.out.printf(".");
		main.sleepTime(200);
		System.out.println();
		service.delateList(number);
		System.out.println("목록으로 돌아갑니다.");
		main.move();

	}

	private void myBoard() {
		MypageService service = new MypageServiceImpl();
		List<CommunityVO> list = service.myBoardList(MemberLog.nowUser.getMemberName());
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		int pagenum = 1;
		while (run) {
			System.out.println("***************************************************************");
			System.out.println();
			System.out.println("                       내   작  성  글         ");
			System.out.println();
			System.out.println("***************************************************************");
			System.out.println();
			myBoardList(pagenum);
			System.out.println();
			System.out.println("                        **" + pagenum + "페이지**");
			System.out.println();
			System.out.println("***************************************************************");
			System.out.println("자세히 보려면 번호를 입력해주세요.");
			System.out.printf(">>");
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (InputMismatchException e) {
				sc.nextLine();
			}
			System.out.println();

			switch (select) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				myBoardSelect(pagenum, select);
				break;
			case 8:
				if (pagenum == 1) {
					main.move();
					myBoardSelect(pagenum, select);
					break;
				} else if (pagenum > 1) {
					pagenum--;
					break;
				}
			case 9:
				pagenum++;
				break;
			case 0:
				System.out.println("이전 화면으로 돌아갑니다.");
				main.move();
				run = false;
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				System.out.println("다시 입력해주세요.");
				main.load();
			}
		}

	}

	private void myBoardList(int pagenum) {
		MypageService service = new MypageServiceImpl();
		List<CommunityVO> list = service.myBoardList(MemberLog.nowUser.getMemberName());
		int pageStart = (7 * (pagenum - 1)) + 1;
		int pageEnd = 7 * pagenum;
		int cnt = 1;
		if (pageStart <= list.size()) {
			if (list.size() < 9) {
				for (int i = 0; i < list.size(); i++) {
					System.out.printf("%d) 제목 : %20s | %s %s", cnt++, list.get(i).getSubject(),
							list.get(i).getCountry(), list.get(i).getLocation());
					System.out.println();
				}
				System.out.println();
				System.out.printf("0) 이전메뉴로");
			} else if (list.size() > 9) {
				if (pagenum == 1) {
					for (int i = 0; i < 8; i++) {
						System.out.printf("%d) 제목 : %20s | %s %s", cnt++, list.get(i).getSubject(),
								list.get(i).getCountry(), list.get(i).getLocation());
						System.out.println();
					}
					System.out.println();
					System.out.printf("9) 다음으로\n0) 이전메뉴로");
				} else if (pagenum > 1) {
					for (int i = 0; i < list.size(); i++) {
						if (i >= pageStart && i <= pageEnd) {
							System.out.printf("%d) %d 제목 : %20s | %s %s", cnt++, list.get(i).getSubject(),
									list.get(i).getCountry(), list.get(i).getLocation());
							System.out.println();

						}
					}
					System.out.println();
					System.out.printf("8) 이전으로\n9) 다음으로\n0) 이전메뉴로");
				}

			}
		} else {
			System.out.println("더 이상 페이지가 없습니다.");
			System.out.println("0을 눌러 이전화면으로 이동하세요.");
		}

	}

	private void myBoardSelect(int page, int line) {
		MypageService service = new MypageServiceImpl();
		CommunityApp communityapp = new CommunityApp();
		CommunityService communityService = new CommunityServiceImpl();
		List<CommunityVO> list = service.myBoardList(MemberLog.nowUser.getMemberName());
		Scanner sc = new Scanner(System.in);
		CommunityVO vo = new CommunityVO();
		int searchBoard = 0;
		if (page == 1) {
			searchBoard = line - 1;
		} else if (page > 1) {
			searchBoard = (7 * (page - 1)) + line;
		}
		for (int i = 0; i < list.size(); i++) {
			if (i == searchBoard) {
				vo = list.get(i);
			}
		}
		vo.toString();
		System.out.println("1.글 수정 2.글 삭제 3.목록으로 4.메인메뉴로");
		System.out.print("입력>>");
		int select = sc.nextInt();
		switch (select) {
		case 1:
			main.move();
			communityapp.modifyBoard(vo);
			break;
		case 2:
			main.move();
			communityapp.deleteBoard(vo.getNumber());
			break;
		case 3:
			System.out.println("목록으로 돌아갑니다.");
			main.sleepTime(400);
			main.move();
			break;
		case 4:
			System.out.println("메인화면으로 돌아갑니다.");
			main.move();
			main.MainList();
			break;
		}

	}

}
