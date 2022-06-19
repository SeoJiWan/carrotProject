package repository;

import java.util.List;

import domain.Product;

public interface ProductRepository {
	
	void insert(Product product);
	
	void update(Product product);
	
	void delete(int proudctId);
	
	Product selectOne(int proudctId);
	
	Product selectOne(String name);
	
	List<Product> selectAll();
}
