package domain;

public class MyTrade {
	
	/*
	 * Field
	 */
	private int orderId;
	private int saleId;
	private int sellerId;
	private int buyerId;
	private String identification;
	private String productName;
	private int orderQuatity;
	private int orderPrice;
	

	/*
	 * Method
	 */
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public int getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getOrderQuatity() {
		return orderQuatity;
	}
	public void setOrderQuatity(int orderQuatity) {
		this.orderQuatity = orderQuatity;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	@Override
	public String toString() {
		return "MyTrade [orderId=" + orderId + ", saleId=" + saleId + ", sellerId=" + sellerId + ", buyerId=" + buyerId
				+ ", identification=" + identification + ", productName=" + productName + ", orderQuatity="
				+ orderQuatity + ", orderPrice=" + orderPrice + "]";
	}
}
