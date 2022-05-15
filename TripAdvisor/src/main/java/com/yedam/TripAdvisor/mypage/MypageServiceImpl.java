package com.yedam.TripAdvisor.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.TripAdvisor.community.CommunityVO;
import com.yedam.TripAdvisor.dao.DataSource;

public class MypageServiceImpl implements MypageService {

	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MypageVO> myTripSightList(String user) { //내 관심여행지
		List<MypageVO> list = new ArrayList<MypageVO>();
		try {
			conn = dao.getConnection();
			String sql = "SELECT * FROM MY_TRIP_SIGHT_LIST WHERE USER = ? ORDER BY number"; // 글 번호순 역정렬(최신글이 보이도록)
			psmt = conn.prepareCall(sql);
			psmt.setString(1, user);
			rs = psmt.executeQuery();
			while (rs.next()) { // 추가 작업 필요
				MypageVO vo = new MypageVO();


				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<CommunityVO> myBoardList(String writter) { //내 글 조회
		List<CommunityVO> list = new ArrayList<CommunityVO>();
		try {
			conn = dao.getConnection();
			String sql = "SELECT * FROM BOARD WHERE WRITTER = ? ORDER BY number DESC"; // 글 번호순 역정렬(최신글이 보이도록)
			psmt = conn.prepareCall(sql);
			psmt.setString(1, writter);
			rs = psmt.executeQuery();
			while (rs.next()) { // 추가 작업 필요
				CommunityVO vo = new CommunityVO();

				vo.setNumber(rs.getInt(""));
				vo.setSubject(rs.getString(""));
				vo.setCountry(rs.getString(""));
				vo.setLocation(rs.getString(""));
				vo.setIndex(rs.getString(""));
				vo.setWritter(rs.getString(""));
				vo.setWrittenDate(rs.getDate(""));

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
