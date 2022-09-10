package domain;

import java.sql.Date;

public class Message {
	
	/*
	 * Field
	 */
	private int messageId;
	private int senderId;
	private int receiverId;
	private String content;
	private Date sendDate;
	private int productId;
	
	

	/*
	 * Method
	 */
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", senderId=" + senderId + ", receiverId=" + receiverId
				+ ", content=" + content + ", sendDate=" + sendDate + "]";
	}
	

	

}
