package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import domain.Sale;

public class MemorySaleRepository implements SaleRepository{

	/*
	 * Field
	 */
	// 싱글톤
	private static SaleRepository saleRepository = null;
	private Map<Integer, Sale> store = new HashMap<Integer, Sale>();
	private static int sequence = 0;
	

	/*
	 * Constructor
	 */
	// 싱글톤
	private MemorySaleRepository() {}

	
	/*
	 * Method
	 */
	// 싱글톤 -> 메서드로 인스턴스 생성
	public static SaleRepository getSaleRepository() {
		if (saleRepository == null) {
			saleRepository = new MemorySaleRepository();
		}
		return saleRepository;
	}
	
	@Override
	public void insert(Sale sale) {
		sale.setSaleId(++sequence);
		sale.setSaleId(++sequence);
		store.put(sale.getSaleId(), sale);
	}

	@Override
	public void update(Sale sale) {
		if (store.containsKey(sale.getSaleId())) {
			store.put(sale.getSaleId(), sale);
		}
		else {
			System.out.println("해당 판매건이 존재하지 않습니다.");
		}
	}

	@Override
	public void delete(int saleId) {
		if (store.containsKey(saleId)) {
			store.remove(saleId);
		} else {
			System.out.println("해당 판매건이 존재하지 않습니다.");
		}
	}

	@Override
	public Sale selectOne(int saleId) {
		if (store.containsKey(saleId)) {
			return store.get(saleId);
		} else {
			System.out.println("해당 판매건이 존재하지 않습니다.");
			return null;
		}
	}

	@Override
	public List<Sale> selectAll() {
		List<Sale> list = new ArrayList<Sale>();

		Set<Integer> set = store.keySet();
		for (Integer key : set) {
			list.add(store.get(key));
		}
		return list;
	}
}
