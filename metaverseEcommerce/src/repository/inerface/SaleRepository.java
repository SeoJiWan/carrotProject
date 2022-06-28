package repository.inerface;

import java.util.List;

import domain.Sale;
import domain.SaleInfo;

public interface SaleRepository {

	void insert(Sale sale);

	void update(Sale sale);
	
	void delete(int saleId);
	
	Sale selectOne(int saleId);
	
	List<SaleInfo> selectAll(int loginMemberId);
	
	List<SaleInfo> selectAllByKeyword(int loginMemberId, String keyword);
	
	List<SaleInfo> selectAllByNeighbor(int address, int prevEmdCd, int nextEmdCd);
}
