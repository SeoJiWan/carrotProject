package domain;

public class Product {
	
	// 필드
	private String productName;
	private int  productPrice;
	
	
	// 생성자
	public Product(String productName, int productPrice) {
		this.productName = productName;
		this.productPrice = productPrice;
	}

	
	// 메서드
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "Product [productName = " + productName + ", productPrice = " + productPrice + "]";
	}
	
	
	
	
	

}
