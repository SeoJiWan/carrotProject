package repository;

import java.util.List;

import domain.Order;

public interface OrderRepository {

	void insert(Order order);
	
	void update(Order order);
	
	void delete(int orderId);

	Order selectOne(int orderId);
	
	List<Order> selectAll();

}
