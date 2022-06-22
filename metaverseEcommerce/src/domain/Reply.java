package domain;

import java.sql.Date;

public class Reply {
	
	/*
	 * Field
	 */
	private int replyId;
	private int boardId;
	private int memberId;
	private String content;
	private Date regDate;

	
	/*
	 * Method
	 */
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "Reply [replyId=" + replyId + ", boardId=" + boardId + ", memberId=" + memberId + ", content="
				+ content + ", regDate=" + regDate + "]";
	}
	

	

}
