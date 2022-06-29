package domain;

import exception.NotEnoughStockException;

public class Product {
	
	/*
	 * Field
	 */
	private int productId;
	private String name;
	private int quantity;
	private int price;
	private String description;
	private String image;

	
	/*
	 * Method - getter, setter
	 */
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", quantity=" + quantity + ", price=" + price
				+ ", description=" + description + "]";
	}
	
	// -- 비즈니스 로직 -- // 
	public void removeQuantity(int orderQuantity, Product product) {
		int restQuantity = product.getQuantity() - orderQuantity;
		
		if (restQuantity < 0) {
			throw new NotEnoughStockException("상품수량이 부족합니다.");
		}
		product.setQuantity(restQuantity);
	}
}
