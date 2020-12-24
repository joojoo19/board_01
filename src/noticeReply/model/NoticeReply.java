package noticeReply.model;

import java.util.Date;

public class NoticeReply {
	private int id;
	private String memberId;
	private int noticeNum;
	private String body;
	private Date regDate;
	private Date modDate;
	private int count;
	
	public NoticeReply(int id, String memberId, int noticeNum, String body, Date regDate) {
		this.id = id;
		this.memberId = memberId;
		this.noticeNum = noticeNum;
		this.body = body;
		this.regDate = regDate;
	}
	public NoticeReply() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getNotideNum() {
		return noticeNum;
	}
	public void setNoticeNum(int noticeNum) {
		this.noticeNum = noticeNum;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getModDate() {
		return modDate;
	}
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	

}
