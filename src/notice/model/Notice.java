package notice.model;

import java.util.Date;

public class Notice {
	private Integer number;
	private Writer writer;
	private String title;
	private Date regDate;
	private Date modDate;
	private int readCount;
	private int replyCount;

	public Notice(Integer number, Writer writer, String title, Date regDate, Date modDate, int readCount) {
		this.number = number;
		this.writer = writer;
		this.title = title;
		this.regDate = regDate;
		this.modDate = modDate;
		this.readCount = readCount;
	}

	public Notice(Integer number, Writer writer, String title, Date regDate, Date modDate, int readCount,
			int replyCount) {
		this.number = number;
		this.writer = writer;
		this.title = title;
		this.regDate = regDate;
		this.modDate = modDate;
		this.readCount = readCount;
		this.replyCount = replyCount;
	}

	public Integer getNumber() {
		return number;
	}

	public Writer getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public Date getRegDate() {
		return regDate;
	}

	public Date getModifiedDate() {
		return modDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public Date getModDate() {
		return modDate;
	}
}
