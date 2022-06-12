package domain;

public class Post {
	// 필드
		private Long orderId; // PK
		private Product product; // FK
		private Member member; // FK
		private int postPrice; // 주문 가격
		private int count; // 주문 수량
		
		
		// 메서드
		public Long getOrderId() {
			return orderId;
		}

		public void setOrderId(Long orderId) {
			this.orderId = orderId;
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

		public int getpostPrice() {
			return postPrice;
		}

		public void setpostPrice(int postPrice) {
			this.postPrice = postPrice;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

}
