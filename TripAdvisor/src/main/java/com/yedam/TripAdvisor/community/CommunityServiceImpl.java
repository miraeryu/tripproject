package com.yedam.TripAdvisor.community;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.TripAdvisor.dao.DataSource;

public class CommunityServiceImpl implements CommunityService {

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
	public List<CommunityVO> selectAllList() { // 전체 글 조회
		List<CommunityVO> list = new ArrayList<CommunityVO>();
		try {
			conn = dao.getConnection();
			String sql = "SELECT * FROM BOARD ORDER BY list_number DESC"; // 글 번호순 역정렬(최신글이 보이도록)
			psmt = conn.prepareCall(sql);
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
		}
		return list;
	}

	@Override
	public List<CommunityVO> countryList() { // 국가 리스트
		List<CommunityVO> list = new ArrayList<CommunityVO>();
		try {
			conn = dao.getConnection();
			String sql = "SELECT DISTINCT COUNTRY_NAME FROM BOARD ORDER BY COUNTRY_NAME"; // 글 번호순 역정렬(최신글이 보이도록)
			psmt = conn.prepareCall(sql);
			rs = psmt.executeQuery();
			while (rs.next()) { // 추가 작업 필요
				CommunityVO vo = new CommunityVO();

				vo.setCountry(rs.getString("country_name"));

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<CommunityVO> selectCountryList(String country) { // 국가별 글 조회 리스트
		List<CommunityVO> list = new ArrayList<CommunityVO>();
		try {
			conn = dao.getConnection();
			String sql = "SELECT * FROM BOARD WHERE COUNTRY_NAME = ? ORDER BY list_number DESC"; // 글 번호순 역정렬(최신글이 보이도록)
			psmt = conn.prepareCall(sql);
			psmt.setString(1, country);
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
		}
		return list;
	}

	@Override
	public void addBoard(CommunityVO vo) { // 글 작성
		int n = 0;
		try {// 추가작업필요
			conn = dao.getConnection();
			String sql = "INSERT INTO BOARD VALUES(BOARD_LIST_NUM_SQ.nextval, ?, ?, ?, ?, SYSDATE, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getCountry());
			psmt.setString(2, vo.getWritter());
			psmt.setString(3, vo.getSubject());
			psmt.setString(4, vo.getLocation());
			psmt.setString(5, vo.getIndex());

			n = psmt.executeUpdate();
			if (n != 0) {
				System.out.println("정상적으로 등록하였습니다.");
			} else {
				System.out.println("등록에 실패하였습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void modify(CommunityVO vo) { // 글 수정
		int n = 0;
		try {
			conn = dao.getConnection();
			String sql = "UPDATE BOARD SET BOARD_SUBJECT = ? , WRITE_INDEX = ?, BOARD_SIGHT = ? WHERE LIST_NUMBER = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getSubject());
			psmt.setString(2, vo.getIndex());
			psmt.setString(3, vo.getLocation());
			psmt.setInt(4, vo.getNumber());

			n = psmt.executeUpdate();
			if (n != 0) {
				System.out.println("정상적으로 수정하였습니다.");
			} else {
				System.out.println("수정에 실패하였습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	@Override
	public void delete(int number) { // 글 삭제
		int n = 0;
		try {
			conn = dao.getConnection();
			String sql = "DELETE FROM BOARD WHERE LIST_NUMBER = ?";
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
		}

	}

	@Override
	public CommunityVO readmore(int number) { // 단건 조회
		CommunityVO vo = new CommunityVO();
		try {
			conn = dao.getConnection();
			String sql = "SELECT * FROM BOARD WHERE LIST_NUMBER = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, number);
			rs = psmt.executeQuery();
			while (rs.next()) { // 추가 작업 필요

				vo.setNumber(rs.getInt("list_number"));
				vo.setCountry(rs.getString("country_name"));
				vo.setWritter(rs.getString("member_name"));
				vo.setSubject(rs.getString("board_subject"));
				vo.setLocation(rs.getString("board_sight"));
				vo.setWrittenDate(rs.getDate("write_date"));
				vo.setIndex(rs.getString("write_index"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vo;
	}

}
