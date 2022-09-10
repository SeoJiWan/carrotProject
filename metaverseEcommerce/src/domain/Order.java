package domain;

public class Order {
	
	/*
	 * Field
	 */
	private int orderId;
	private int buyerId;
	private int saleId;
	private int orderPrice;
	private int orderQuantity;


	/*
	 * Method
	 */
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", buyerId=" + buyerId + ", saleId=" + saleId + ", orderPrice="
				+ orderPrice + ", orderQuantity=" + orderQuantity + "]";
	}
	
}
