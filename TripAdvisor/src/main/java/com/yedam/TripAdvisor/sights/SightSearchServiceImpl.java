package com.yedam.TripAdvisor.sights;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.TripAdvisor.dao.DataSource;

public class SightSearchServiceImpl implements SightSearchService {

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
	public List<SightVO> selectAllList() {
		List<SightVO> list = new ArrayList<SightVO>();
		try {
			conn = dao.getConnection();
			String sql = "SELECT * FROM SIGHT_LIST ORDER BY HIT";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				SightVO vo = new SightVO();
				vo.setCountry(rs.getString("sight_country_name"));
				vo.setName(rs.getString("sight_name"));
				vo.setHit(rs.getInt("hit"));

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

	@Override
	public List<SightVO> countryList() {
		List<SightVO> list = new ArrayList<SightVO>();
		try {
			conn = dao.getConnection();
			String sql = "SELECT DISTINCT SIGHT_COUNTRY_NAME FROM SIGHT_LIST ORDER BY SIGHT_COUNTRY_NAME";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				SightVO vo = new SightVO();
				vo.setCountry(rs.getString("sight_country_name"));

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

	@Override
	public List<SightVO> selectCountryList(String country) {
		List<SightVO> list = new ArrayList<SightVO>();
		try {
			conn = dao.getConnection();
			String sql = "SELECT * FROM SIGHT_LIST WHERE SIGHT_COUNTRY_NAME = ? ORDER BY HIT DESC";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, country);
			rs = psmt.executeQuery();
			while (rs.next()) {
				SightVO vo = new SightVO();
				vo.setCountry(rs.getString("sight_country_name"));
				vo.setName(rs.getString("sight_name"));
				vo.setHit(rs.getInt("hit"));

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public SightVO readMoreSight(String name) {
		SightVO vo = new SightVO();
		try {
			conn = dao.getConnection();
			String sql = "SELECT * FROM SIGHT_LIST WHERE SIGHT_NAME = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo.setCountry(rs.getString("sight_country_name"));
				vo.setName(rs.getString("sight_name"));
				vo.setLocation(rs.getString("sight_location"));
				vo.setTime(rs.getString("sight_opentime"));
				vo.setIndex(rs.getString("sight_index"));
				vo.setHit(rs.getInt("hit"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	@Override
	public void updateHit(String name) {
		try {
			conn = dao.getConnection();
			String sql = "UPDATE SIGHT_LIST SET HIT = HIT + 1 WHERE SIGHT_NAME = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	@Override
	public void myListSave() {

	}

}
