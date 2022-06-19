package repository;

import java.util.List;

import domain.Sale;

public interface SaleRepository {

	void insert(Sale sale);

	void update(Sale sale);
	
	void delete(int saleId);
	
	Sale selectOne(int saleId);
	
	List<Sale> selectAll();
}
