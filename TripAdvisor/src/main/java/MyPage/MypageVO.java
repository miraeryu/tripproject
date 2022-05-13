package MyPage;

import java.util.Date;

import lombok.Data;

@Data
public class MypageVO {
	private String title;
	private String index;
	private int BoardNum;
	private String writer;
	private Date writeDate;

	public MypageVO(String title, int boardNum, Date writeDate) {
		super();
		this.title = title;
		BoardNum = boardNum;
		this.writeDate = writeDate;
	}

	public String toString() { //게시글 조회 toString
		return "MypageVO [subject=" + title + ", index=" + index + ", BoardNum=" + BoardNum + ", writer=" + writer
				+ ", writeDate=" + writeDate + "]";
	}

}
