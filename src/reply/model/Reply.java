package reply.model;

import java.sql.Timestamp;
import java.util.Date;

public class Reply {
	private int id;
	private String memberId;
	private int articleNum;
	private String body;
	private Date regDate;
	private Date modDate;
	

	public Reply(int id, String memberId, int articleNum, String body, Date regDate) {
		this.id = id;
		this.memberId = memberId;
		this.articleNum = articleNum;
		this.body = body;
		this.regDate = regDate;
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

}
