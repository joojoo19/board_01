package noticeReply.model;

import java.util.Date;

public class NoticeReply {
	private int id;
	private int level;
	private int replyId;
	private int groupId;
	private String memberId;
	private int noticeNo;
	private String body;
	private Date regDate;
	private Date modDate;

	public NoticeReply(int id, int replyId, int groupId, String memberId, int noticeNo, String body, Date regDate,
			Date modDate) {
		this.id = id;
		this.replyId = replyId;
		this.groupId = groupId;
		this.memberId = memberId;
		this.noticeNo = noticeNo;
		this.body = body;
		this.regDate = regDate;
		this.modDate = modDate;
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

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
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

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
