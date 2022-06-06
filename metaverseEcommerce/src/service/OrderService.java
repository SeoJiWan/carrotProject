package service;

import domain.Member;
import domain.Order;
import domain.Product;
import repository.MemberRepository;
import repository.MemoryMemberRepository;
import repository.MemoryProductRepository;
import repository.OrderRepository;
import repository.ProductRepository;

public class OrderService {
	
	/*
	 * 필드
	 */
	private OrderRepository orderRepository;
	private MemberRepository memberRepository = MemoryMemberRepository.getMemberRepository();
	private ProductRepository productRepository = MemoryProductRepository.getMemoryProductRepository();
	
	
	/*
	 * 생성자
	 */
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	
	/*
	 * 메서드
	 */
	// 주문
	public void order(Long memberId, String productName, int count) {
		
		Member member = memberRepository.findById(memberId);
		Product product = productRepository.findByName(productName);
		
		Order order = createOrder(member, product, product.getPrice(), count);
		orderRepository.save(order);
		member.addOrder(order);
	}
	
	// 주문 내역 생성
	public Order createOrder(Member member, Product product, int orderPrice, int count) {
		Order order = new Order();
		order.setMember(member);
		order.setProduct(product);
		order.setOrderPrice(orderPrice);
		order.setCount(count);
		
		productRepository.removeStock(count, product);
		
		return order;
	}
	
	// 주문 내역 확인
	public Order findOrder(Long id) {
		return orderRepository.findById(id);
	}

}
