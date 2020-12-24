package noticeReply.service;

import java.util.Map;

public class NoticeReplyModifyRequest {
	private int replyid;
	private String userId;
	private String body;
	
	public NoticeReplyModifyRequest(int replyid, String userId, String body) {
		super();
		this.replyid = replyid;
		this.userId = userId;
		this.body = body;
	}

	/**
	 * @return the replyid
	 */
	public int getReplyid() {
		return replyid;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	public void validate(Map<String, Boolean> errors) {
		if(body == null || body.trim().isEmpty()) {
			errors.put("body", Boolean.TRUE);
		}
	}
	
	
}
