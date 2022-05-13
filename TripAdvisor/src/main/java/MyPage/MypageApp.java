package MyPage;

import java.util.List;
import java.util.Scanner;

import com.yedam.TripAdvisor.Currency.CurrencyDataImpl;
import com.yedam.TripAdvisor.Currency.CurrencyDataVO;
import com.yedam.TripAdvisor.List.AppList;
import com.yedam.TripAdvisor.Member.MemberLog;
import com.yedam.TripAdvisor.Member.MemberServiceImpl;
import com.yedam.TripAdvisor.Member.MemberVO;

public class MypageApp {
	
	public void mypageMain() {
		boolean run = true;
		while(run) {
			System.out.println("****************************");
			System.out.println();
			System.out.println("        마 이 페 이 지         ");
			System.out.println();
			System.out.println("****************************");
			System.out.println("1.내정보\n2.관심여행지\n3.내게시글\n4.돌아가기");
			System.out.print("입력>>");
			Scanner sc = new Scanner(System.in);
			int mypageSelect = sc.nextInt();
			switch(mypageSelect) {
			case 1:
				//실행되는지 확인 필요 : 실행안됨
				MemberLog.nowUser.toString();
				//가능하면 마이페이지로 돌아갈지 메뉴로 돌아갈지 구현
				sleepTime(800);
				break;
				
			case 2:
				
				break;
				
			case 3:
				
				break;
			case 4:
				System.out.println("메뉴로 돌아갑니다.");
				run = false;
				sleepTime(800);
				AppList applist = new AppList();
				applist.MainList();
				break;
			}
		}
	}
	
	//관심여행지 : 여행지에서 내가 관심잇는 정보 불러오기
	//내 게시글 : 작성자로 DB에서 검색

	public List<MypageVO> myBoardList(){
		List<MypageVO> list;
		
		
		return null;
	}
	
	private void sleepTime(int sec) {
		long c = System.currentTimeMillis();
		while (true) {
			if ((System.currentTimeMillis() - c) >= sec) {
				break;
			}
		}
	}
}
