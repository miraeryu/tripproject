package com.yedam.TripAdvisor.community;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.yedam.TripAdvisor.List.AppList;
import com.yedam.TripAdvisor.Member.MemberLog;
import com.yedam.TripAdvisor.sights.SightSearchService;
import com.yedam.TripAdvisor.sights.SightSearchServiceImpl;
import com.yedam.TripAdvisor.sights.SightVO;

public class CommunityApp {
	AppList main = new AppList();

	public void communityMain() {

		boolean run = true;

		while (run) {
			// 1. 전체보기 2.국가별로보기
			System.out.println("***************************************************************");
			System.out.println();
			System.out.println("                        커  뮤  니  티          ");
			System.out.println();
			System.out.println("***************************************************************");
			System.out.println("메뉴를 선택하세요.");
			System.out.println("1.전체글\n2.국가별 커뮤니티\n3.메인화면으로");
			System.out.println("***************************************************************");
			System.out.println(">>");
			Scanner sc = new Scanner(System.in);
			int choice = 0;
			try {
				choice = sc.nextInt();
			} catch (InputMismatchException e) {
				sc.nextLine();
			}
			switch (choice) {
			case 1:
				main.move();
				communityAllList();
				break;
			case 2:
				main.move();
				countryList();
				break;
			case 3:
				System.out.println("메인 화면으로 돌아갑니다.");
				main.sleepTime(600);
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

	private void communityAllList() {
//		List<CommunityVO> list = new ArrayList<CommunityVO>();
//		CommunityService service = new CommunityServiceImpl();
		Scanner sc = new Scanner(System.in);

		int pagenum = 1;
		boolean run = true;

		while (run) {
			System.out.println("***************************************************************");
			System.out.println();
			System.out.println("                      전  체  글  보  기 ");
			System.out.println();
			System.out.println("***************************************************************");

			CommunityAllListPage(pagenum);
			System.out.println();
			System.out.println("                        **" + pagenum + "페이지**");
			System.out.println("***************************************************************");
			System.out.println("자세히 보고 싶다면 번호를 입력하세요.");
			System.out.print(">>");
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (InputMismatchException e) {
				sc.nextLine();
			}
			switch (select) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				main.move();
				communityAllListSelect(pagenum, select);
				break;
			case 8:
				if (pagenum == 1) {
					main.move();
					communityAllListSelect(pagenum, select);
					break;
				} else if (pagenum > 1) {
					pagenum--;
					break;
				}
			case 9:
				pagenum++;
				break;
			case 0:
				System.out.println("이전 메뉴로 돌아갑니다.");
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

	private int CommunityAllListPage(int pagenum) {
		CommunityService service = new CommunityServiceImpl();
		List<CommunityVO> list = service.selectAllList();
		int pageStart = (7 * (pagenum - 1)) + 1;
		int pageEnd = 7 * pagenum;
		int cnt = 1;
		if (pageStart <= list.size()) {

			if (list.size() < 9) {
				for (int i = 0; i < list.size(); i++) {
					System.out.printf("%d) 제목 : %20s | 작성자 : %s | %s %s", cnt++, list.get(i).getSubject(),
							list.get(i).getWritter(), list.get(i).getCountry(), list.get(i).getLocation());
					System.out.println();
				}
				System.out.println();
				System.out.printf("0) 이전메뉴로");
			} else if (list.size() > 9) {
				if (pagenum == 1) {
					for (int i = 0; i < 8; i++) {
						System.out.printf("%d) 제목 : %20s | 작성자 : %s | %s %s", cnt++, list.get(i).getSubject(),
								list.get(i).getWritter(), list.get(i).getCountry(), list.get(i).getLocation());
						System.out.println();
					}
					System.out.println();
					System.out.printf("9) 다음으로\n0) 이전메뉴로");
				} else if (pagenum > 1) {
					for (int i = 0; i < list.size(); i++) {
						if (i >= pageStart && i <= pageEnd) {
							System.out.printf("%d) %d 제목 : %20s | 작성자 : %s | %s %s", cnt++, list.get(i).getSubject(),
									list.get(i).getWritter(), list.get(i).getCountry(), list.get(i).getLocation());
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

		return 0;
	}

	private void communityAllListSelect(int page, int line) {
		CommunityService service = new CommunityServiceImpl();
		List<CommunityVO> list = service.selectAllList();
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
		System.out.println("1.전체글보기 2.메인메뉴로");
		System.out.print("입력>>");
		int select = sc.nextInt();
		switch (select) {
		case 1:
			System.out.println("전체글 목록으로 돌아갑니다.");
			main.sleepTime(600);
			main.move();
			break;
		case 2:
			System.out.println("메인화면으로 돌아갑니다.");
			main.move();
			AppList main = new AppList();
			main.MainList();
			break;
		}

	}

	private void countryList() {
		Scanner sc = new Scanner(System.in);
		int pagenum = 1;
		boolean run = true;

		while (run) {
			System.out.println("***************************************************************");
			System.out.println();
			System.out.println("                  국  가  별  커  뮤  니  티 ");
			System.out.println();
			System.out.println("***************************************************************");
			countryList(pagenum);
			System.out.println();
			System.out.println("                        **" + pagenum + "페이지**");
			System.out.println("***************************************************************");
			System.out.println("보고 싶은 커뮤니티를 선택해주세요.");
			System.out.print(">>");
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
				main.move();
				countrySelect(pagenum, select);
				break;
			case 8:
				if (pagenum == 1) {
					main.move();
					countrySelect(pagenum, select);
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

	private void countryList(int pagenum) {
		CommunityService service = new CommunityServiceImpl();
		List<CommunityVO> list = service.countryList();
		int pageStart = (7 * (pagenum - 1)) + 1;
		int pageEnd = 7 * pagenum;
		int cnt = 1;
		if (pageStart <= list.size()) {

			if (list.size() < 9) {
				for (int i = 0; i < list.size(); i++) {
					System.out.printf("%d) %s", cnt++, list.get(i).getCountry());
					System.out.println();
				}
				System.out.println();
				System.out.printf("0) 이전메뉴로");
			} else if (list.size() > 9) {
				if (pagenum == 1) {
					for (int i = 0; i < 8; i++) {
						System.out.printf("%d) %s", cnt++, list.get(i).getCountry());
						System.out.println();
					}
					System.out.println();
					System.out.printf("9) 다음으로\n0) 이전메뉴로");
				} else if (pagenum > 1) {
					for (int i = 0; i < list.size(); i++) {
						if (i >= pageStart && i <= pageEnd) {
							System.out.printf("%d) %s", cnt++, list.get(i).getCountry());
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

	private void countrySelect(int page, int line) {
		CommunityService service = new CommunityServiceImpl();
		List<CommunityVO> list = service.countryList();
		Scanner sc = new Scanner(System.in);
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

		countryPage(countryName);
	}

	public void countryPage(String countryName) {
		Scanner sc = new Scanner(System.in);
		int pagenum = 1;
		int selectInt = 0;
		boolean run = true;

		while (run) {
			System.out.println("***************************************************************");
			System.out.println();
			System.out.println("                           " + countryName);
			System.out.println();
			System.out.println("***************************************************************");
			countryBoardList(pagenum, countryName);
			System.out.println();
			System.out.println("                        **" + pagenum + "페이지**");
			System.out.println("***************************************************************");
			System.out.println("자세히 보고 싶다면 번호를 입력하세요.");
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
				main.move();
				selectInt = Integer.parseInt(select);
				countryBoardSelect(pagenum, selectInt, countryName);
				break;
			case "8":
				if (pagenum == 1) {
					main.move();
					selectInt = Integer.parseInt(select);
					countryBoardSelect(pagenum, selectInt, countryName);
					break;
				} else if (pagenum > 1) {
					pagenum--;
					break;
				}
			case "9":
				pagenum++;
				break;
			case "0":
				System.out.println("이전 화면으로 돌아갑니다.");
				main.move();
				run = false;
				break;
			case "*":
				addBoard(countryName);
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				System.out.println("다시 입력해주세요.");
				main.load();

			}

		}

	}

	private void countryBoardList(int pagenum, String countryName) {
		CommunityService service = new CommunityServiceImpl();
		List<CommunityVO> list = service.selectCountryList(countryName);
		int pageStart = (7 * (pagenum - 1)) + 1;
		int pageEnd = 7 * pagenum;
		int cnt = 1;

		if (list.size() == 0) {
			System.out.println("현재 해당 게시판에 작성된 글이 없습니다.");
			System.out.println();
			System.out.printf("0) 이전메뉴로 \n*) 새글쓰기");
		} else if (list.size() != 0) {
			if (pageStart <= list.size()) {
				if (list.size() < 9) {
					for (int i = 0; i < list.size(); i++) {
						System.out.printf("%d) 제목 : %20s | 작성자 : %s | %s %s", cnt++, list.get(i).getSubject(),
								list.get(i).getWritter(), list.get(i).getCountry(), list.get(i).getLocation());
						System.out.println();
					}
					System.out.println();
					System.out.printf("0) 이전메뉴로 \n*) 새글쓰기");
				} else if (list.size() > 9) {
					if (pagenum == 1) {
						for (int i = 0; i < 8; i++) {
							System.out.printf("%d) 제목 : %20s | 작성자 : %s | %s %s", cnt++, list.get(i).getSubject(),
									list.get(i).getWritter(), list.get(i).getCountry(), list.get(i).getLocation());
							System.out.println();
						}
						System.out.println();
						System.out.printf("9) 다음으로\n0) 이전메뉴로 \n*)새글쓰기");
					} else if (pagenum > 1) {
						for (int i = 0; i < list.size(); i++) {
							if (i >= pageStart && i <= pageEnd) {
								System.out.printf("%d) %d 제목 : %20s | 작성자 : %s | %s %s", cnt++,
										list.get(i).getSubject(), list.get(i).getWritter(), list.get(i).getCountry(),
										list.get(i).getLocation());
								System.out.println();

							}
						}
						System.out.println();
						System.out.printf("8) 이전으로\n9) 다음으로\n0) 이전메뉴로 \n*) 새글쓰기");
					}
				}
			} else {
				System.out.println("더 이상 페이지가 없습니다.");
				System.out.println("0을 눌러 이전화면으로 이동하세요.");
			}

		}

	}

	private void countryBoardSelect(int page, int line, String countryName) {
		CommunityService service = new CommunityServiceImpl();
		List<CommunityVO> list = service.selectCountryList(countryName);
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
		if (vo.getWritter().equals(MemberLog.nowUser.getMemberName())) {
			System.out.println("1.글 수정 2.글 삭제 3.목록으로 4.메인메뉴로");
			System.out.print("입력>>");
			int select = sc.nextInt();
			switch (select) {
			case 1:
				main.move();
				modifyBoard(vo);
				break;
			case 2:
				main.move();
				deleteBoard(vo.getNumber());
				break;
			case 3:
				System.out.println("목록으로 돌아갑니다.");
				main.sleepTime(600);
				main.move();
				break;
			case 4:
				System.out.println("메인화면으로 돌아갑니다.");
				main.move();
				AppList main = new AppList();
				main.MainList();
				break;
			}
		} else {
			System.out.println("1.목록으로 2.메인메뉴로");
			System.out.print("입력>>");
			int select = sc.nextInt();
			switch (select) {
			case 1:
				System.out.println("목록으로 돌아갑니다.");
				main.sleepTime(600);
				main.move();
				break;
			case 2:
				System.out.println("메인화면으로 돌아갑니다.");
				main.move();
				AppList main = new AppList();
				main.MainList();
				break;
			}
		}

	}

	private void addBoard(String countryName) {
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
		System.out.print("장소>>");
		String newLocation = sc.nextLine();
		vo.setLocation(newLocation);
		System.out.print("내용>>");
		String newIndex = sc.nextLine();
		vo.setIndex(newIndex);
		vo.setCountry(countryName);
		vo.setWritter(MemberLog.nowUser.getMemberName());
		service.addBoard(vo);
		System.out.println("목록으로 돌아갑니다.");
		main.move();

	}

	public void modifyBoard(CommunityVO vo) {
		CommunityService service = new CommunityServiceImpl();
		Scanner sc = new Scanner(System.in);
		CommunityVO modify = new CommunityVO();
		System.out.println();
		System.out.println("***************************************************************");
		System.out.println();
		System.out.println("                      게  시  글  수  정");
		System.out.println();
		System.out.println("***************************************************************");
		System.out.println("제목을 수정하시겠습니까? (Y/N)");
		System.out.println(">>");
		String subjectYes = sc.nextLine();
		if (subjectYes.equals("Y") | subjectYes.equals("y")) {
			System.out.println("글 제목 수정>>");
			String newSubject = sc.nextLine();
			modify.setSubject(newSubject);
		} else if (subjectYes.equals("N") | subjectYes.equals("n")) {
			modify.setSubject(vo.getSubject());
		} else {
			System.out.println("잘못된 입력입니다.");
			main.sleepTime(400);
			return;
		}
		System.out.println("장소를 수정하시겠습니까? (Y/N)");
		System.out.println(">>");
		String locationYes = sc.nextLine();
		if (locationYes.equals("Y") | locationYes.equals("y")) {
			System.out.print("장소 수정>>");
			String newLocation = sc.nextLine();
			modify.setLocation(newLocation);
		} else if (locationYes.equals("N") | locationYes.equals("n")) {
			modify.setLocation(vo.getLocation());
		} else {
			System.out.println("잘못된 입력입니다.");
			main.sleepTime(400);
			return;
		}
		System.out.println("내용을 수정하시겠습니까? (Y/N)");
		System.out.println(">>");
		String indexYes = sc.nextLine();
		if (indexYes.equals("Y") | indexYes.equals("y")) {
			System.out.println("글 내용 수정>>");
			String newIndex = sc.nextLine();
			modify.setIndex(newIndex);
		} else if (indexYes.equals("N") | indexYes.equals("y")) {
			modify.setIndex(vo.getIndex());
		} else {
			System.out.println("잘못된 입력입니다.");
			main.sleepTime(400);
			return;
		}
		System.out.println();
		modify.setNumber(vo.getNumber());
		service.modify(modify);
		System.out.println("목록으로 돌아갑니다.");
		main.move();

	}

	public void deleteBoard(int number) {
		CommunityService service = new CommunityServiceImpl();
		System.out.println("게시글을 삭제합니다.");
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
		service.delete(number);
		System.out.println("목록으로 돌아갑니다.");
		main.move();
	}

}
