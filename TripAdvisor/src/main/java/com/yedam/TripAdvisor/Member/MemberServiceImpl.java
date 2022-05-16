package com.yedam.TripAdvisor.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.yedam.TripAdvisor.dao.DataSource;

public class MemberServiceImpl implements MemberService {
	
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	MemberVO member;
	Scanner sc = new Scanner(System.in);

	@Override
	public void MemberInsert(MemberVO vo) {
		int n = 0;
		try {
			conn = dao.getConnection(); 
			String sql = "INSERT INTO MEMBER VALUES(?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			psmt.setString(2, vo.getMemberPw());
			psmt.setString(3, vo.getMemberName());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		if (n != 0) {
			System.out.println("회원가입 성공. 로그인을 해주세요.");			
		}else {
			System.out.println("회원가입에 실패하였습니다. 규격에 맞게 다시 진행해주세요.");
		}
		
	}

	@Override
	public MemberVO Login(MemberVO vo) {
		MemberVO sigh = new MemberVO();
		String sql = "select * from MEMBER where member_id = ?";
		try {
			conn = dao.getConnection(); 
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			rs = psmt.executeQuery();
			if(rs.next()){
				sigh.setMemberId(rs.getString("member_id"));
				sigh.setMemberPw(rs.getString("member_pw"));
				sigh.setMemberName(rs.getString("member_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(); 
		}
		return sigh;
	}
	
	public void close() {
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
