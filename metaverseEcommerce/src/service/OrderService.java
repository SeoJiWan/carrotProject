package service;

import java.util.List;

import domain.Order;
import domain.TopInfo;
import repository.inerface.OrderRepository;

public class OrderService {
	
	/*
	 * Field
	 */
	private final OrderRepository orderRepository;
	
	
	/*
	 * Constructor
	 */
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	
	/*
	 * Method
	 */
	// 주문 생성
	public void createOrder(Order order) {
		orderRepository.insert(order);
	}
	
	// 주문 수정
	public void updateOrder(Order order) {
		orderRepository.update(order);
	}
	
	// 주문 삭제
	public void deleteOrder(int orderId) {
		orderRepository.delete(orderId);
	}
	
	// 주문 단건조회
	public Order findOneOrder(int orderId) {
		return orderRepository.selectOne(orderId);
	}
	
	// 주문 전체조회
	public List<Order> findAllOrders() {
		return orderRepository.selectAll();
	}

	// 구매왕
	public TopInfo findTopBuyer() {
		return orderRepository.topBuyer();
	}

	// 구매왕
	public TopInfo findTopSeller() {
		return orderRepository.topSeller();
	}
}
