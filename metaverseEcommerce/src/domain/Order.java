package domain;

public class Order {
	
	// 필드
	private static Long orderId = 0L; // PK
	private Product product; // FK
	private Member member; // FK
	private int orderPrice; // 주문 가격
	private int count; // 주문 수량
	
	
	// 메서드
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		orderId++;
		Order.orderId = orderId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Order details [buyer = " + member.getName() +
				", seller = " + product.getSeller() + 
				", productName = " + product.getName() + 
				", productPrice = " + orderPrice +
				", orderQuantity = " + count + 
				", totalPrice = " + (orderPrice * count) +
				"]";
				
	}
	
	

}
