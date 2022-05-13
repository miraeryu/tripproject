package com.yedam.TripAdvisor.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.TripAdvisor.dao.DataSource;

public class CurrencyDataImpl implements CurrencyData {

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
	public List<CurrencyDataVO> listCurrency() {
		List<CurrencyDataVO> list = new ArrayList<CurrencyDataVO>();
		String sql = "SELECT * FROM COUNTRY_CODE ORDER BY COUNTRY_LIST_NUMBER";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				CurrencyDataVO vo = new CurrencyDataVO(rs.getString("country_id"), rs.getString("country_name"),
						rs.getString("currency"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

}
