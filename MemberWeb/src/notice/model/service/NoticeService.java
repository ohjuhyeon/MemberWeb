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

}