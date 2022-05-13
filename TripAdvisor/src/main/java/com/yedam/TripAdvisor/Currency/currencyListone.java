package com.yedam.TripAdvisor.Currency;

import java.util.List;
import java.util.Scanner;

import com.yedam.TripAdvisor.List.AppList;

public class currencyListone { // Crun 으로 옮김 >> 사용하지 않음
	Scanner sc = new Scanner(System.in);

	public void execute() {
		CurrencyDataImpl service = new CurrencyDataImpl();
		List<CurrencyDataVO> list = service.listCurrency();
		boolean b = true;
		int run = 1;
		int pagenum = 1;
		int cnt = 1;

		while(b){
			if (pagenum == 1) {
				for (int i = 0; i < 7; i++) {
					System.out.printf("%d) %s %s", cnt++, list.get(i).getCountryId(), list.get(i).getCountryName());
					System.out.println();

				}
				System.out.println("9) 다음으로\n0) 메인화면으로");
			}
			System.out.println("****************************");
			System.out.printf("입력>>");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				int[] pagecode = new int[] { pagenum, choice };
				exchangeCalculator(pagecode);
				break;

			case 8:
				pagenum--;
				page((pagenum));
				break;
			case 9:
				pagenum++;
				page((pagenum));
				break;
			case 0:
				System.out.println("메인 화면으로 돌아갑니다.");
				b = false;
				AppList main = new AppList();
				main.MainList();
				break;

			}
		} 
	}

	public int page(int pagenum) { // 페이징
		CurrencyDataImpl service = new CurrencyDataImpl();
		List<CurrencyDataVO> list = service.listCurrency();

		int pageStart = 7 * (pagenum - 1);
		int pageEnd = (7 * pagenum) - 1;
		int cnt = 1;

		for (int i = 0; i < list.size(); i++) {
			if (i >= pageStart && i <= pageEnd) {
				System.out.printf("%d) %s %s", cnt++, list.get(i).getCountryId(), list.get(i).getCountryName());
				System.out.println();
			}
		}
		if (pagenum > 1) {
			System.out.println("8) 이전으로\n9) 다음으로\n0) 메인화면으로");
		}
		return pagenum++;

	}

	public int[] exchangeCalculator(int[] pagecodesearch) {
		int page = pagecodesearch[0];
		int line = pagecodesearch[1];
		CurrencyDataImpl service = new CurrencyDataImpl();
		List<CurrencyDataVO> list = service.listCurrency();
		String currencyCode = null;
		int searchCurrency = 0;
		if (page == 1) {
			searchCurrency = line;
		} else if (page != 1) {
			searchCurrency = (7 * (page -1)) + line;
		}
		for (int i = 0; i < list.size(); i++) {
			if ((i+1) == searchCurrency) {
				currencyCode = list.get(i).getCountryId();
			}
		}
    	CurrencyService cs = new CurrencyService();
    	double exchangeRate = CurrencyService.getCurrencyInfomation(currencyCode);
    	System.out.println("해당 국가의 현재 환율은 " + exchangeRate + "입니다.");
    	if (currencyCode.equals("JPY(100)") | currencyCode.equals("ESP(100)") 
    			| currencyCode.equals("IDR(100)") | currencyCode.equals("ITL(100)")) {
    		System.out.println("해당 국가의 화폐는 100 단위로 출력됩니다.");
    	}
    	exchange(exchangeRate);
		return null;
	}
	
	public double exchange(double exchangeRate) {
		System.out.println("금액을 입력해주세요.");
		System.out.println("금액은 정수로만 입력 가능합니다.");
		int choice = sc.nextInt();
		double result = choice * exchangeRate;
		System.out.println("계산 결과 : " +result); 
		
		return result;
	}

}
