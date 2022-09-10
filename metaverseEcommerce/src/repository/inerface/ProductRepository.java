package repository.inerface;

import java.util.List;

import domain.Product;

public interface ProductRepository {
	
	void insert(Product product);
	
	void update(int proudctId, int productQuantity);
	
	void delete(int productId);
	
	Product selectOne(int productId);
	
	Product selectOne(String name);
	
	List<Product> selectAll();
}
