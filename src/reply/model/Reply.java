package reply.model;

import java.sql.Timestamp;
import java.util.Date;

import com.sun.javafx.collections.SetAdapterChange;

public class Reply {
	private int id;
	private int level;
	private int replyId;
	private int groupId;
	private String memberId;
	private int articleNum;
	private String body;
	private Date regDate;
	private Date modDate;



	public Reply(int id, int replyId, int groupId, String memberId, int articleNum, String body,
			Date regDate, Date modDate) {
		this.id = id;
		this.replyId = replyId;
		this.groupId = groupId;
		this.memberId = memberId;
		this.articleNum = articleNum;
		this.body = body;
		this.regDate = regDate;
		this.modDate = modDate;
	}

	public Reply() {

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

	public int getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(int articleNum) {
		this.articleNum = articleNum;
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
