package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import member.model.vo.Member;

public class MemberDAO {

	public Member selectList(Connection conn, String userId, String userPwd) {

		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				member = new Member();
				member.setUserId(rset.getString("MEMBER_ID"));
				member.setUserPwd(rset.getString("MEMBER_PWD"));
				member.setUserName(rset.getString("MEMBER_NAME"));
				member.setAge(rset.getInt("AGE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setGender(rset.getString("GENDER"));
				member.setHobby(rset.getString("HOBBY"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return member;

	}

	public Member selectOne(Connection conn, String userId) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				member = new Member();
				member.setUserId(rset.getString("MEMBER_ID"));
				member.setUserPwd(rset.getString("MEMBER_PWD"));
				member.setUserName(rset.getString("MEMBER_NAME"));
				member.setAge(rset.getInt("AGE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setGender(rset.getString("GENDER"));
				member.setHobby(rset.getString("HOBBY"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return member;
	}


	public ArrayList<Member> selectMemberList(Connection conn) {
		ArrayList<Member> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<>();
			while(rset.next()) {
				Member member = new Member();
				member.setUserId(rset.getString("MEMBER_ID"));
				member.setUserPwd(rset.getString("MEMBER_PWD"));
				member.setUserName(rset.getString("MEMBER_NAME"));
				member.setAge(rset.getInt("AGE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setGender(rset.getString("GENDER"));
				member.setHobby(rset.getString("HOBBY"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
				list.add(member);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?,?,sysdate)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,  member.getUserId());
			pstmt.setString(2,  member.getUserPwd());
			pstmt.setString(3,  member.getUserName());
			pstmt.setString(4,  member.getGender());
			pstmt.setInt(5,  member.getAge());
			pstmt.setString(6,  member.getEmail());
			pstmt.setString(7,  member.getPhone());
			pstmt.setString(8,  member.getAddress());
			pstmt.setString(9,  member.getHobby());
			
			result = pstmt.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return result;
	}

	public int deleteMember(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateMember(Connection conn, Member member) {
		int result = 0 ;
		PreparedStatement pstmt = null;
		String query = "UPDATE MEMBER SET MEMBER_PWD=?,PHONE=?,ADDRESS=?,EMAIL=?,GENDER=?,HOBBY=? WHERE MEMBER_ID=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getUserPwd());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getGender());
			pstmt.setString(6, member.getHobby());
			pstmt.setString(7, member.getUserId());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return result;
	}

}
