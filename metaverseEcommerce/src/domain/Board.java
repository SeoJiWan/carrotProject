package domain;

import java.util.Date;

public class Board {
	
	/*
	 * Field
	 */
	private int boardId;
	private int memberId;
	private String title;
	private String content;
	private Date regDate;
	

	/*
	 * Method - getter, setter
	 */
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
		return "Board [boardId=" + boardId + ", memberId=" + memberId + ", title=" + title + ", content=" + content
				+ ", regDate=" + regDate + "]";
	}
	
}
