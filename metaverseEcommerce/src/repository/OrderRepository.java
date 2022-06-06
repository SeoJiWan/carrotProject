package repository;

import domain.Order;

public interface OrderRepository {
	
	void save(Order order);
	
	
	
	Order findById(Long id);

}
