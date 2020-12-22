package notice.service;

import notice.model.Notice;
import notice.model.NoticeContent;

public class NoticeData {

	private Notice notice;
	private NoticeContent content;
	
	private int prevNumber;
	private int nextNumber;
	
	public NoticeData(Notice notice, NoticeContent content) {
		this.notice = notice;
		this.content = content;
	}
	
	public NoticeData(Notice notice, NoticeContent content, int prevNumber, int nextNumber) {
		this.notice = notice;
		this.content = content;
		this.prevNumber = prevNumber;
		this.nextNumber = nextNumber;
	}

	public Notice getNotice() {
		return notice;
	}

	public String getContent() {
		return content.getContent();
	}
	
	public int getNextNumber() {
		return nextNumber;
	}
	
	public int getPrevNumber() {
		return prevNumber;
	}
	

	
}
