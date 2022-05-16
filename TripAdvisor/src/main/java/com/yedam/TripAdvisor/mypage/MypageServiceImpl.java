package com.yedam.TripAdvisor.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.yedam.TripAdvisor.Member.MemberLog;
import com.yedam.TripAdvisor.community.CommunityService;
import com.yedam.TripAdvisor.community.CommunityServiceImpl;
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
	public List<MypageVO> myTripSightList(String user) { // 내 관심여행지
		List<MypageVO> list = new ArrayList<MypageVO>();
		try {
			conn = dao.getConnection();
			String sql = "SELECT * FROM MY_SIGHT_LIST WHERE USER_NAME = ? ORDER BY list_number"; // 글 번호순 역정렬(최신글이 보이도록)
			psmt = conn.prepareCall(sql);
			psmt.setString(1, MemberLog.nowUser.getMemberName());
			rs = psmt.executeQuery();
			while (rs.next()) { // 추가 작업 필요
				MypageVO vo = new MypageVO();
				vo.setListNumber(rs.getInt("list_number"));
				vo.setCountry(rs.getString("sight_country"));
				vo.setSightName(rs.getString("sight_name"));
				vo.setUser(rs.getString("user_name"));

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}

	@Override
	public List<CommunityVO> myBoardList(String writter) { // 내 글 조회
		List<CommunityVO> list = new ArrayList<CommunityVO>();
		try {
			conn = dao.getConnection();
			String sql = "SELECT * FROM BOARD WHERE MEMBER_NAME = ? ORDER BY list_number DESC"; // 글 번호순 역정렬(최신글이 보이도록)
			psmt = conn.prepareCall(sql);
			psmt.setString(1, writter);
			rs = psmt.executeQuery();
			while (rs.next()) { // 추가 작업 필요
				CommunityVO vo = new CommunityVO();

				vo.setNumber(rs.getInt("list_number"));
				vo.setCountry(rs.getString("country_name"));
				vo.setWritter(rs.getString("member_name"));
				vo.setSubject(rs.getString("board_subject"));
				vo.setLocation(rs.getString("board_sight"));
				vo.setWrittenDate(rs.getDate("write_date"));
				vo.setIndex(rs.getString("write_index"));

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}

	@Override
	public void addList(MypageVO vo) {
		int n = 0;
		try {
			conn = dao.getConnection();
			String sql = "INSERT INTO MY_SIGHT_LIST VALUES(?, ?, ?, SIGHT_LIST_NUMBER.nextval)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, MemberLog.nowUser.getMemberName());
			psmt.setString(2, vo.getCountry());
			psmt.setString(3, vo.getSightName());

			n = psmt.executeUpdate();
			if (n != 0) {
				System.out.println("정상적으로 등록하였습니다.");
			} else {
				System.out.println("등록에 실패하였습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
	}

	@Override
	public void delateList(int number) {
		int n = 0;
		try {
			conn = dao.getConnection();
			String sql = "DELETE FROM MY_SIGHT_LIST WHERE LIST_NUMBER = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, number);
			n = psmt.executeUpdate();
			if (n != 0) {
				System.out.println("정상적으로 삭제하였습니다.");
			} else {
				System.out.println("삭제에 실패하였습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}

	}

}
