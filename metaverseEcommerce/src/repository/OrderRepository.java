package repository;

import java.util.List;

import domain.Order;

public interface OrderRepository {

	void save(Order order);

	Order findById(Long id);
	
	public List<Order> findAll();

}
