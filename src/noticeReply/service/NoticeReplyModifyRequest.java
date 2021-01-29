package noticeReply.service;

import java.util.Map;

public class NoticeReplyModifyRequest {
	private int id;
	private String userId;
	private String body;
	
	public NoticeReplyModifyRequest(int id, String userId, String body) {
		this.id = id;
		this.userId = userId;
		this.body = body;
	}

	/**
	 * @return the replyid
	 */
	public int getId() {
		return id;
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
