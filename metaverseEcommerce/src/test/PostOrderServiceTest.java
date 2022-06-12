package test;

import java.util.List;
import domain.Member;
import domain.Order;
import domain.Product;
import repository.MemoryMemberRepository;
import repository.MemoryOrderRepository;
import repository.MemoryPostRepository;
import repository.MemoryProductRepository;
import service.MemberService;
import service.OrderService;
import service.PostService;
import service.ProductService;

public class PostOrderServiceTest {
	
	public static void main(String[] args) {
		
		OrderService orderService = new OrderService(MemoryOrderRepository.getMemoryOrderRepository());
		PostService postService = new PostService(MemoryPostRepository.getMemoryPostRepository());
		MemberService memberService = new MemberService(MemoryMemberRepository.getMemberRepository());
		ProductService productService = new ProductService(MemoryProductRepository.getMemoryProductRepository());
		
		// 멤버 인스턴스 생성
		Member member1 = new Member();
		member1.setId(123L);
		member1.setName("wana");
		
		Member member2	= new Member();
		member2.setId(1234L);
		member2.setName("woo");
		// 회원가입
		memberService.join(member1);
		memberService.join(member2);
		
		// 상품 인스턴스 생성
		Product product1 = new Product();
		product1.setProductId(1L);
		product1.setPrice(1000);
		product1.setStockQuantity(3);
		product1.setName("LG 울르라 와이드 모니터");
		
		Product product2 = new Product();
		product2.setProductId(2L);
		product2.setPrice(100000);
		product2.setStockQuantity(4);
		product2.setName("Asus TUF gaming");
		
		// 상품 게시
		postService.post(member1.getId(), product1, product1.getStockQuantity());
		postService.post(member2.getId(), product2, product2.getStockQuantity());
		
		// 게시 상품 확인
		List<Product> findAllProducts = productService.findAllProducts();
		findAllProducts.forEach(System.out::println);
		
		// 주문 생성
		orderService.order(member1.getId(), product2.getName(), 2);
		orderService.order(member2.getId(), product1.getName(), 1);
		
//		// 주문 확인 - 멤버별 주문
//		List<Order> orders1 = member1.getOrders();
//		orders1.forEach(System.out::println);
//		List<Order> orders2 = member2.getOrders();
//		orders2.forEach(System.out::println);
		
		// 주문 확인 - 전체 주문
		List<Order> findAllOrders = orderService.findAllOrders();
		findAllOrders.forEach(System.out::println);
//		System.out.println(findAllOrders.size()); // 1?
		
		// 주문 후 상품변화 확인
		findAllProducts.forEach(System.out::println);
		
		
	}

}
