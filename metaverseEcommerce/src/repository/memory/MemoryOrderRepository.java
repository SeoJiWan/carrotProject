package repository.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import domain.Order;
import domain.TopInfo;
import repository.inerface.OrderRepository;

public class MemoryOrderRepository implements OrderRepository {

	/*
	 * Field
	 */
	// 싱글톤
	private static OrderRepository orderRepository = null;
	private static Map<Integer, Order> store = new HashMap<Integer, Order>();
	private static int sequence = 0;

	/*
	 * Constructor
	 */
	// 싱글톤
	private MemoryOrderRepository() {
	}

	/*
	 * Method
	 */
	// 싱글톤 -> 메서드로 인스턴스 생성
	public static OrderRepository getOrderRepository() {
		if (orderRepository == null) {
			orderRepository = new MemoryOrderRepository();
		}
		return orderRepository;
	}

	@Override
	public void insert(Order order) {
		order.setOrderId(++sequence);
		store.put(order.getOrderId(), order);
	}

	@Override
	public void update(Order order) {
		if (store.containsKey(order.getOrderId())) {
			store.put(order.getOrderId(), order);
		} else {
			System.out.println("해당 주문이 존재하지 않습니다.");
		}
	}

	@Override
	public void delete(int orderId) {
		if (store.containsKey(orderId)) {
			store.remove(orderId);
		} else {
			System.out.println("해당 주문이 존재하지 않습니다.");
		}
	}

	@Override
	public Order selectOne(int orderId) {
		if (store.containsKey(orderId)) {
			return store.get(orderId);
		} else {
			System.out.println("해당 주문이 존재하지 않습니다.");
			return null;
		}
	}

	@Override
	public List<Order> selectAll() {
		List<Order> list = new ArrayList<Order>();

		Set<Integer> set = store.keySet();
		for (Integer key : set) {
			list.add(store.get(key));
		}
		return list;
	}

	@Override
	public TopInfo topBuyer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TopInfo topSeller() {
		// TODO Auto-generated method stub
		return null;
	}

}
