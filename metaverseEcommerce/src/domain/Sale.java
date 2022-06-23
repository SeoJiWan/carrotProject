package domain;

public class Sale {
	
	/*
	 * Field
	 */
	private int saleId;
	private int sellerId;
	private int productId;
	private String saleStatus;
	

	/*
	 * Method - getter, setter
	 */
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
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getSaleStatus() {
		return saleStatus;
	}
	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}
	@Override
	public String toString() {
		return "Sale [saleId=" + saleId + ", sellerId=" + sellerId + ", productId=" + productId + ", saleStatus="
				+ saleStatus + "]";
	}

	

}
