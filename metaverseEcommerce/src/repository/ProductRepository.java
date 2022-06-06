package repository;

import java.util.List;

import domain.Product;

public interface ProductRepository {
	
	void save(Product product);
	
	Product findByName(String name);
	
	List<Product> findAll();
	
	public void removeStock(int quantity, Product product);

}
