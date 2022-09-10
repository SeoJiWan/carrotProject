package service;

import java.util.List;

import domain.MyTrade;
import domain.Order;
import domain.Product;
import domain.Sale;
import domain.TopInfo;
import repository.inerface.OrderRepository;
import repository.inerface.ProductRepository;
import repository.inerface.SaleRepository;
import repository.jdbc.JdbcProductRepository;
import repository.jdbc.JdbcSaleRepository;

public class OrderService {
	
	/*
	 * Field
	 */
	private final OrderRepository orderRepository;
	private SaleRepository saleRepository  = JdbcSaleRepository.getSaleRepository();
	private ProductRepository productRepository = JdbcProductRepository.getProductRepository();
	
	
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
		
		// 주문 생성 시점 --> 해당 상품의 수량 업데이트 (주문후 수량 = 상품수량 - 주문수량)
		// order -> sale -> product 으로 엔티티가 접근
		Sale sale = saleRepository.selectOne(order.getSaleId());
		Product product = productRepository.selectOne(sale.getProductId());
		// 주문 후 수량 계산 로직 - 엔티티에 비즈니스 로직으로 있음.
		product.removeQuantity(order.getOrderQuantity(), product);
		// 주문 후 수량으로 product tbl 업데이트
		productRepository.update(product.getProductId(), product.getQuantity());
		
		
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
	
	// 내 구매내역 조회
	public List<MyTrade> findMyOrders(int buyerId) {
		return orderRepository.selectMyOrders(buyerId);
	}
	
	// 내 판매내역 조회
	public List<MyTrade> findMySales(int sellerId) {
		return orderRepository.selectMySales(sellerId);
	}

	// 구매왕
	public List<TopInfo> findTopBuyer() {
		return orderRepository.topBuyer();
	}

	// 구매왕
	public List<TopInfo> findTopSeller() {
		return orderRepository.topSeller();
	}
}
