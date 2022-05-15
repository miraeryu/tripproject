
public interface CommunityService {
	
	List<CommunityVO> selectAllList();//전체글보기
	
	List <CommunityVO> countryList();//국가 출력
	
	List <CommunityVO> selectCountryList(String country);//국가별 게시판 보기
	
	void addBoard();//글 등록
	
	void modify();//글 수정
	
	void delete();//글 삭제
	
	CommunityVO readmore();//글 1건 보기

}
