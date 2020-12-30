package reply.service;

import java.util.Map;

public class ReplyModifyRequest {
	private int id;
	private String userId;
	private String body;

	public ReplyModifyRequest(int id, String userId, String body) {
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

	public String getBody() {
		return body;
	}

	public String getUserId() {
		return userId;
	}

	public void validate(Map<String, Boolean> errors) {
		if (body == null || body.trim().isEmpty()) {
			errors.put("body", Boolean.TRUE);
		}
	}

}
