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
	private int product_id;
	private int emdCd;
	private String productImage;
	
	
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
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getEmdCd() {
		return emdCd;
	}
	public void setEmdCd(int emdCd) {
		this.emdCd = emdCd;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	@Override
	public String toString() {
		return "상품이름\t:\t" + productName +
				"\n\n상품가격\t:\t" + productPrice + 
				"\n\n상품수량\t:\t" + productQuantity +  
				"\n\n판매자\t:\t" + identification + 
				"\n\n판매상태\t:\t" + saleStatus +
				"\n\n판매지역\t:\t" + address +
				"\n\n상품설명\t:\t" + productDescription;
	}
}
