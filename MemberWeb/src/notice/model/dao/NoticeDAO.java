package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import notice.model.vo.Notice;
import notice.model.vo.NoticeComment;

public class NoticeDAO {

	public ArrayList<Notice> selectNoticeList(Connection conn, int currentPage, int recordCountPerPage) {
		// Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> nList = new ArrayList<Notice>();
		String query = "SELECT * FROM (SELECT NOTICE.*, ROW_NUMBER() OVER(ORDER BY NOTICENO DESC) AS NUM FROM NOTICE) WHERE NUM BETWEEN ? AND ?";

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;

		try {
			// stmt = conn.createStatement();
			// rset = stmt.executeQuery(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Notice notice = new Notice();
				notice.setNoticeNo(rset.getInt("NOTICENO"));
				notice.setContents(rset.getString("CONTENTS"));
				notice.setSubject(rset.getString("SUBJECT"));
				notice.setUserId(rset.getString("USERID"));
				notice.setRegDate(rset.getDate("REGDATE"));
				nList.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return nList;
	}

	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM NOTICE";
		// 게시글 총 갯수를 알아오는 쿼리
		try {

			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recordTotalCount;
	}

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		int recordTotalCount = totalCount(conn);
		int pageTotalCount = 0; // 전체 페이지의 개수
		// 전체 게시물 갯수 124개, 10개씩 페이지를 만들면 페이지 갯수는 13개

		// 만들 전체 페이지의 개수
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		// 현재 페이지를 기준으로 네비게이션을 구해야하므로
		// 현재 페이지 정보를 확인해서 0보다는 크고 전체 페이지 수보다는 작은 위치에 있는지 확인
		// (오류방지용)
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		int startNavi = ((currentPage - 1) / naviCountPerPage * naviCountPerPage + 1);
		// 1 2 3 4 5 6
		// currentPage = 8, naviCountPerPage =5
		// ((8-1/5)/*5+1) => 6
		// currentPage = 42, naviCountPerPage = 5
		// 41 42 43 44 45
		// ((42-1)/5)*5+1 => 41
		// 오류방지
		int endNavi = startNavi + naviCountPerPage - 1;

		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		// < 1 2 3 4 5 >
		// '<'모양과'>' 모양을 준비하기 위해 필요한 변수
		boolean needPrev = true;
		boolean needNext = true;

		if (startNavi == 1) {
			needPrev = false;

		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}

		// 모든 준비는 끝남
		StringBuilder sb = new StringBuilder();
		if (needPrev) {
			sb.append("<a href = '/notice?currentPage=" + (startNavi - 1) + "'> < </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<a href='/notice?currentPage=" + i + "'><b>" + i + "</b></a>");
			} else {
				sb.append("<a href='/notice?currentPage=" + i + "'>" + i + "</a>");
			}
		}
		if (needNext) {
			sb.append("<a href='/notice?currentPage=" + (endNavi + 1) + "'>></a>");
		}
		return sb.toString();
	}

	public ArrayList<Notice> noticeSearchList(Connection conn, int currentPage, int recordCountPerPage, String search) {
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		String query = "SELECT * FROM (SELECT NOTICE.*, ROW_NUMBER() OVER(ORDER BY NOTICENO DESC) AS NUM FROM NOTICE WHERE SUBJECT LIKE '%"
				+ search + "%') WHERE NUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Notice notice = new Notice();
				notice.setNoticeNo(rset.getInt("NOTICENO"));
				notice.setContents(rset.getString("CONTENTS"));
				notice.setSubject(rset.getString("SUBJECT"));
				notice.setUserId(rset.getString("USERID"));
				notice.setRegDate(rset.getDate("REGDATE"));
				list.add(notice);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public int searchTotalCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM NOTICE WHERE SUBJECT LIKE '%" + search + "%'";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recordTotalCount;
	}

	public String getSearchPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage,
			String search) {
		int recordTotalCount = searchTotalCount(conn, search);

		int pageTotalCount = 0;
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}

		// 오류방지용
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}

		int startNavi = ((currentPage - 1) / naviCountPerPage * naviCountPerPage + 1);
		int endNavi = startNavi + naviCountPerPage - 1;
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		boolean needPrev = true;
		boolean needNext = true;

		if (startNavi == 1) {
			needPrev = false;

		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}

		StringBuilder sb = new StringBuilder();
		if (needPrev) {
			sb.append("<a href='/noticesearch?search=" + search + "&currentPage=" + (startNavi - 1) + "'> < </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<a href='/noticesearch?search=" + search + "&currentPage=" + i + "'><b>" + i + "</b></a>");
			} else {
				sb.append("<a href='/noticesearch?search=" + search + "&currentPage=" + i + "'>" + i + "</a>");
			}
		}
		if (needNext) {
			sb.append("<a href='/noticesearch?search=" + search + "&currentPage=" + (endNavi + 1) + "'>></a>");
		}
		return sb.toString();

	}

	public int insertNotice(Connection conn, String subject, String content, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO NOTICE VALUES(SEQ_NOTICE.NEXTVAL,?,?,?,SYSDATE)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setString(3, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;

	}

	public Notice noticeSelect(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice notice = null;
		String query = "SELECT * FROM NOTICE WHERE NOTICENO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				notice = new Notice();
				notice.setNoticeNo(rset.getInt("NOTICENO"));
				notice.setSubject(rset.getString("SUBJECT"));
				notice.setContents(rset.getString("CONTENTS"));
				notice.setUserId(rset.getString("USERID"));
				notice.setRegDate(rset.getDate("REGDATE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return notice;
	}

	public int modifyNotice(Connection conn, String subject, String content, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE NOTICE SET SUBJECT=?, CONTENTS=?, REGDATE=SYSDATE WHERE NOTICENO =?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setInt(3, noticeNo);
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

	public int deleteNotice(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM NOTICE WHERE NOTICENO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate(); //쿼리문 실행
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

	public ArrayList<NoticeComment> noticeComment(Connection conn, int noticeNo) {
		//SELECT * FROM NOTICECOMMENT WHERE NOTICEDNO =?
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NoticeComment> list = new ArrayList<NoticeComment>();
		String query = "SELECT * FROM NOTICECOMMENT WHERE NOTICENO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				NoticeComment comment = new NoticeComment();
				comment.setCommentNo(rset.getInt("COMMENTNO"));
				comment.setNoticeNo(rset.getInt("NoticeNo"));
				comment.setContent(rset.getString("CONTENT"));
				comment.setUserId(rset.getString("USERID"));
				comment.setRegDate(rset.getDate("REGDATE"));
				list.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}

}