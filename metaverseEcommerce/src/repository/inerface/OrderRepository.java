package repository.inerface;

import java.util.List;

import domain.MyTrade;
import domain.Order;
import domain.TopInfo;

public interface OrderRepository {

	void insert(Order order);
	
	void update(Order order);
	
	void delete(int orderId);

	Order selectOne(int orderId);
	
	List<Order> selectAll();
	
	List<MyTrade> selectMyOrders(int buyerId);
	
	List<MyTrade> selectMySales(int sellerId);
	
	List<TopInfo> topBuyer();
	
	List<TopInfo> topSeller();

}
