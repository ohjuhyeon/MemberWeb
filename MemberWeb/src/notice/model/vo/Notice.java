package notice.model.vo;

import java.sql.Date;
import java.util.ArrayList;

public class Notice {

	private int noticeNo;
	private String subject;
	private String userId;
	private String contents;
	private Date regDate;
	private ArrayList<NoticeComment> comments = null;
	// 댓글

	public Notice() {
	}

	public Notice(int noticeNo, String subject, String userId, String contents, Date regDate) {
		super();
		this.noticeNo = noticeNo;
		this.subject = subject;
		this.userId = userId;
		this.contents = contents;
		this.regDate = regDate;
	}

	public ArrayList<NoticeComment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<NoticeComment> comments) {
		this.comments = comments;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	

}
