package notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;
import notice.model.vo.PageData;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/notice")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NoticeServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. 전송값에 한글이 있을경우 인코딩
		// 2. View 에서 보낸 전송값 변수 저장
		// 3. 비지니스 로직을 처리할 서비스 클래스 메소드로 값을 전달 및 결과 받기
		int currentPage = 0;
		// href="/notice?currentPage=1" 페이지를 누를때마다.
		if (request.getParameter("currentPage") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		PageData pageData = new NoticeService().selecetNoticeList(currentPage);

		RequestDispatcher view = request.getRequestDispatcher("/views/notice/noticeAll.jsp");
		request.setAttribute("PageData", pageData);
		view.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}