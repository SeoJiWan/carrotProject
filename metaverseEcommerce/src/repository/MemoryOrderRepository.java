package repository;

import java.util.HashMap;
import java.util.Map;

import domain.Order;

public class MemoryOrderRepository implements OrderRepository {

	/*
	 * 필드
	 */
	private static MemoryOrderRepository memoryOrderRepository = new MemoryOrderRepository();
	private static Map<Long, Order> store = new HashMap<Long, Order>();
	
	
	/*
	 * 생성자
	 */
	private MemoryOrderRepository() {};
	
	
	/*
	 * 메서드
	 */
	public static MemoryOrderRepository getMemoryOrderRepository() {
		return memoryOrderRepository;
	}
	
	@Override
	public void save(Order order) {
		store.put(order.getOrderId(), order);
	}

	@Override
	public Order findById(Long id) {
		return store.get(id);
	}

}
