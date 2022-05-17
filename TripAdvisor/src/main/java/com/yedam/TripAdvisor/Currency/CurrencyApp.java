package com.yedam.TripAdvisor.Currency;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.yedam.TripAdvisor.List.AppList;

public class CurrencyApp {
	Scanner sc = new Scanner(System.in);
	AppList main = new AppList();

	public void run() {
		CurrencyDataImpl service = new CurrencyDataImpl();
		List<CurrencyDataVO> list = service.listCurrency();
		boolean run = true;
		int pagenum = 1;
		int cnt = 1;

		while (run) {
			System.out.println("***************************************************************");
			System.out.println();
			System.out.println("                     환  율  계  산  기        ");
			System.out.println();
			System.out.println("***************************************************************");
			System.out.println();
			page(pagenum);
			System.out.println("                        **" + pagenum +"페이지**");
			System.out.println();
			System.out.println("***************************************************************");
			System.out.println("통화를 선택하세요.");
			System.out.printf(">>");
			int select = 0;
			try {
				select = sc.nextInt();

			} catch (InputMismatchException e) {
				sc.nextLine();
			}
			int[] pagecode = new int[] { pagenum, select };
			switch (select) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				exchangeCalculator(pagecode);
				break;

			case 8:
				if (pagenum == 1) {
					exchangeCalculator(pagecode);
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
				main.sleepTime(400);
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

	public int page(int pagenum) { // 페이징
		CurrencyDataImpl service = new CurrencyDataImpl();
		List<CurrencyDataVO> list = service.listCurrency();

		int pageStart = (7 * (pagenum - 1)) + 1;
		int pageEnd = (7 * pagenum);
		int cnt = 1;
		
		if (pageStart <= list.size()) {
			
			if (pagenum == 1) {
				for (int i = 0; i < 8; i++) {
					System.out.printf("%d) %s %s %s", cnt++, list.get(i).getCountryId(), list.get(i).getCountryName(),
							list.get(i).getCurrency());
					System.out.println();
					
				}
				System.out.println();
				System.out.println("9) 다음으로\n0) 메인화면으로");
			} else if (pagenum > 1) {
				for (int i = 0; i < list.size(); i++) {
					if (i >= pageStart && i <= pageEnd) {
						System.out.printf("%d) %s %s %s", cnt++, list.get(i).getCountryId(), list.get(i).getCountryName(),
								list.get(i).getCurrency());
						System.out.println();
					}
				}
				System.out.println();
				System.out.println("8) 이전으로\n9) 다음으로\n0) 메인화면으로");
				
			}
		}else {
			System.out.println("더 이상 페이지가 없습니다.");
			System.out.println("0을 눌러 메인화면으로 이동하세요.");
		}

		return 0;

	}

	public int[] exchangeCalculator(int[] pagecodesearch) {
		int page = pagecodesearch[0];
		int line = pagecodesearch[1];
		CurrencyDataImpl service = new CurrencyDataImpl();
		List<CurrencyDataVO> list = service.listCurrency();
		String currencyCode = null;
		String currencyName = null;
		String countryName = null;
		int searchCurrency = 0;
		if (page == 1) {
			searchCurrency = line - 1;
		} else if (page != 1) {
			searchCurrency = (7 * (page - 1)) + line;
		}
		for (int i = 0; i < list.size(); i++) {
			if (i == searchCurrency) {
				currencyCode = list.get(i).getCountryId();
				currencyName = list.get(i).getCurrency();
				countryName = list.get(i).getCountryName();
			}
		}
		CurrencyService cs = new CurrencyService();
		double exchangeRate = CurrencyService.getCurrencyInfomation(currencyCode);
		if (exchangeRate == 0.0) {
			System.out.println("해당 국가는 현재 서버 문제로 조회할 수 없습니다.");
			System.out.println("환율계산기로 돌아갑니다.");
			main.move();
			main.sleepTime(600);
		} else {
			if (currencyCode.equals("JPY(100)") | currencyCode.equals("ESP(100)") | currencyCode.equals("IDR(100)")
					| currencyCode.equals("ITL(100)")) {
				System.out.println(countryName + " 100" + currencyName + "의 현재 환율은 " + exchangeRate + "원입니다.");
				System.out.println("해당 국가의 화폐는 100 단위로만 입력할 수 있습니다.");
			}else {
				System.out.println(countryName + " 1" + currencyName + "의 현재 환율은 " + exchangeRate + "원입니다.");
			}

			exchange(exchangeRate);
		}
		return null;
	}

	public double exchange(double exchangeRate) {
		System.out.println("금액을 입력해주세요.");
		System.out.println("금액은 정수로만 입력 가능합니다.");
		System.out.print(">>");
		int choice = sc.nextInt();
		double result = choice * exchangeRate;
		System.out.println("계산 결과 : " + result + "원");
		System.out.println();
		System.out.println("***************************************************************");
		System.out.println("환율계산기로 돌아가겠습니까?");
		System.out.println("1.환율계산기 2.메인화면");
		System.out.print(">>");
		int reset = sc.nextInt();
		System.out.println();
		if (reset == 1) {
			System.out.println("환율계산기로 돌아갑니다.");
			main.move();
			main.sleepTime(600);
		} else if (reset == 2) {
			System.out.println("메인화면으로 돌아갑니다.");
			main.move();
			main.sleepTime(600);
			AppList app = new AppList();
			app.MainList();
		}
		return result;
	}


}
