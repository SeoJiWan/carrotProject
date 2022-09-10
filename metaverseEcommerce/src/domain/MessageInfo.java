package domain;

import java.sql.Date;

public class MessageInfo {
	
	/*
	 * Field
	 */
	private int messageId;
	private int senderId;
	private String Senderidentification;
	private int receiverId;
	private String content;
	private Date sendDate;
	private int productId;
	private String productName;


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
	public String getSenderidentification() {
		return Senderidentification;
	}
	public void setSenderidentification(String senderidentification) {
		Senderidentification = senderidentification;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Override
	public String toString() {
		return "MessageInfo [Senderidentification="+ Senderidentification 
				+ ", content=" + content 
				+ ", sendDate=" + sendDate 
				+ ", productName=" + productName + "]";
	}
	

	

}
