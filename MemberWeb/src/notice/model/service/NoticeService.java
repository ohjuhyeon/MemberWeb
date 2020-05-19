package notice.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;
import notice.model.vo.PageData;

public class NoticeService {
   
   
      // SELECT * FROM NOTICE
      
      private ConnectionFactory factory;
      
      public NoticeService() {
         factory = ConnectionFactory.getConnection();
      }
      
      public PageData selecetNoticeList(int currentPage){
         
         Connection conn = null;
         int recordCountPerPage = 10;
         int naviCountPerPage=5; //1 2 3 4 5 네비게이션의 갯수 지정
         PageData pd = new PageData();
         ArrayList<Notice>noticeList = null;
         
         try {
         conn = factory.createConnection();
         noticeList =new NoticeDAO().selectNoticeList(conn, currentPage, recordCountPerPage);
         pd.setPageList(noticeList);
         pd.setPageNavi(new NoticeDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
      }catch(SQLException e) {
         e.printStackTrace();
      }
      return pd;
      
   }

	public PageData noticeSearchList(int currentPage, String search) {
		Connection conn = null;
		int recordCountPerPage = 10;
        int naviCountPerPage=5; //1 2 3 4 5 네비게이션의 갯수 지정
        PageData pd = new PageData();
        try {
			conn = factory.createConnection();
	        pd.setPageList(new NoticeDAO().noticeSearchList(conn, currentPage, recordCountPerPage, search));
	        pd.setPageNavi(new NoticeDAO().getSearchPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage, search));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pd;
	}

	public int insertNotice(String subject, String content, String userId) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new NoticeDAO().insertNotice(conn,subject,content,userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(result>0) {
			factory.commit(conn);
		}else {
			factory.rollback(conn);
		}
		return result;
	}

	public Notice noticeSelect(int noticeNo) {
		Connection conn = null;
		Notice notice = null;
		
		try {
			conn = factory.createConnection();
			notice = new NoticeDAO().noticeSelect(conn, noticeNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
	}

	public int modifyNotice(String subject, String content, int noticeNo) {
		
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new NoticeDAO().modifyNotice(conn,subject,content,noticeNo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result>0) {
			factory.commit(conn);
		}
		else {
			factory.rollback(conn);
		}
		return result;
	}

}