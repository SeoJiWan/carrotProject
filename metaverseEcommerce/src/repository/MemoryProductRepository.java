package repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import domain.Product;
import exception.NotEnoughStockException;

public class MemoryProductRepository implements ProductRepository {

	/*
	 * 필드
	 */
	// 싱글톤 인스턴스 생성
	private static MemoryProductRepository memoryProductRepository = new MemoryProductRepository();
	// 상품 저장소
	private static Map<String, Product> store = new HashMap<String, Product>(); 

	
	/*
	 * 생성자
	 */
	private MemoryProductRepository() {

	}

	
	/*
	 * 메서드
	 */
	// 싱글톤 -> 메서드 활용해 private 인스턴스 생성
	public static MemoryProductRepository getMemoryProductRepository() {
		return memoryProductRepository;
	}

	// 상품 저장
	@Override
	public void save(Product product) {
		store.put(product.getName(), product);
	}
	
	// 상품 삭제 -> 회원이 구매시 상품정보를 삭제하고 회원의 장바구니에 담음
	@Override
	public void removeStock(int quantity, Product product) {
        int restStock = product.getStockQuantity() - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        product.setStockQuantity(restStock);
    }

	// 상품 검색
	@Override
	public Product findByName(String name) {
		return store.get(name);
	}

	// 모든 상품 검색
	@Override
	public List<Product> findAll() {
		List<Product> list = new LinkedList<Product>();
		for (Map.Entry<String, Product> entry : store.entrySet()) {
			Product val = entry.getValue();
			list.add(val);
		}
		return list;
	}
	
	

}
