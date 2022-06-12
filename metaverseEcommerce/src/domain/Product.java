package domain;

import java.util.ArrayList;
import java.util.List;

public class Product {
	
	// 필드
	private Long productId;
	private String name;
	private int  price;
	private int stockQuantity;
	private List<Order> orders = new ArrayList<>();
	private String seller;


	// 메서드
	@Override
	public String toString() {
		return "Product [seller = " + getSeller() + 
				", productName = " + name + 
				", productPrice = " + price + 
				", stockQuantity = " + stockQuantity + 
				"]";
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}
	
	
}
