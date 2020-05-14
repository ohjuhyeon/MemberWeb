package member.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import common.ConnectionFactory;
import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class MemberService {

	private ConnectionFactory factory;

	public MemberService() {
		factory = ConnectionFactory.getConnection();
	}

	public Member selectMember(String userId, String userPwd) {

		Connection conn = null;
		Member member = null;
		try {
			conn = factory.createConnection();
			member = new MemberDAO().selectList(conn, userId, userPwd);
			return member;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;

	}

	public Member selectMemberOne(String userId) {
		Connection conn = null;
		Member member = null;

		try {
			conn = factory.createConnection();
			member = new MemberDAO().selectOne(conn, userId);
			return member;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;

	}

}
