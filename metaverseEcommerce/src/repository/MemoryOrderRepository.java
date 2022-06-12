package repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import domain.Order;

public class MemoryOrderRepository implements OrderRepository {

	/*
	 * 필드
	 */
	private static MemoryOrderRepository memoryOrderRepository = new MemoryOrderRepository();
	private static Map<Long, Order> store = new HashMap<Long, Order>();
	
	
	/*
	 * 메서드
	 */
	public static MemoryOrderRepository getMemoryOrderRepository() {
		return memoryOrderRepository;
	}
	
	@Override
	public void save(Order order) {
//		System.out.println(order.getOrderId());
		store.put(order.getOrderId(), order);
	}

	@Override
	public Order findById(Long id) {
		return store.get(id);
	}
	
	@Override
	public List<Order> findAll() {
		List<Order> list = new LinkedList<Order>();
		for (Map.Entry<Long, Order> entry : store.entrySet()) {
			Order val = entry.getValue();
			list.add(val);
		}
		return list;
	}

}
