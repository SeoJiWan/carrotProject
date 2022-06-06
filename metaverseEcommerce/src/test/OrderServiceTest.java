package test;

import java.util.List;
import domain.Member;
import domain.Order;
import domain.Product;
import repository.MemoryMemberRepository;
import repository.MemoryOrderRepository;
import repository.MemoryProductRepository;
import service.MemberService;
import service.OrderService;
import service.ProductService;

public class OrderServiceTest {
	
	public static void main(String[] args) {
		
		OrderService orderService = new OrderService(MemoryOrderRepository.getMemoryOrderRepository());
		MemberService memberService = new MemberService(MemoryMemberRepository.getMemberRepository());
		ProductService productService = new ProductService(MemoryProductRepository.getMemoryProductRepository());
		
		// 멤버 인스턴스 생성
		Member member1 = new Member();
		member1.setId(123L);
		member1.setName("wana");
		// 회원가입
		memberService.join(member1);
		
		// 상품 인스턴스 생성
		Product product1 = new Product();
		product1.setProductId(1L);
		product1.setPrice(1000);
		product1.setStockQuantity(3);
		product1.setName("LG 울르라 와이드 모니터");
		// 상품 게시
		productService.post(product1);
		
		// 주문 생성
		orderService.order(member1.getId(), product1.getName(), 2);
		
		// 주문 확인
		List<Order> orders = member1.getOrders();
		int count = orders.get(0).getCount();
		System.out.println(count);
		
		int stockQuantity = product1.getStockQuantity();
		System.out.println(stockQuantity);
	}

}
