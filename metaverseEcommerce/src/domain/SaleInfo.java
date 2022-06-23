package domain;

public class SaleInfo {
	
	/*
	 * Field
	 */
	private int saleId;
	private int sellerId;
	private String identification;
	private String saleStatus;
	private String productName;
	private int productQuantity;
	private int productPrice;
	private String productDescription;
	private String address;
	

	/*
	 * Method
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
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getSaleStatus() {
		return saleStatus;
	}
	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "상품이름 : " + productName +
				"\n\n상품가격 : " + productPrice + 
				"\n\n상품수량 : " + productQuantity +  
				"\n\n판매자 : " + identification + 
				"\n\n판매상태 : " + saleStatus +
				"\n\n상품설명 : " + productDescription;
	}
	
//	@Override
//	public String toString() {
//		return "상품이름 : " + productName +
//				", 상품가격 : " + productPrice;
//	}
//	


}
