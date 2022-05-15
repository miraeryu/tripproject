@data //롬복 실행
public class CommunityVO {
	private String subject;
	private String country;
	private String location;
	private String index;
	private String writter;
	private Date writtenDate;
	

	@Override
	public String toString() {
		System.out.println("****************************");
		System.out.println("제목 : " + subject);
		System.out.println("작성자 : " + writter +"  작성일 : " +writtenDate 
				+ "\n국가 : " + country + "  장소 : " + location 
				+ "\n내용 : " + index);
		System.out.println("****************************");

		return null;
	}
}
