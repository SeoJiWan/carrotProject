package repository.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import domain.Product;
import repository.inerface.ProductRepository;

public class MemoryProductRepository implements ProductRepository {

	/*
	 * Field
	 */
	// 싱글톤
	private static ProductRepository productRepository = null;
	private static Map<Integer, Product> store = new HashMap<Integer, Product>();
	private static int sequence = 0;

	
	/*
	 * Constructor
	 */
	// 싱글톤
	private MemoryProductRepository() {}

	/*
	 * Method
	 */
	// 싱글톤 -> 메서드로 인스턴스 생성
	public static ProductRepository getProductRepository() {
		if (productRepository == null) {
			productRepository = new MemoryProductRepository();
		}
		return productRepository;
	}
	
	@Override
	public void insert(Product product) {
		product.setProductId(++sequence);
		store.put(product.getProductId(), product);
	}

	@Override
	public void update(Product product) {
		if (store.containsKey(product.getProductId())) {
			store.put(product.getProductId(), product);
		}
		else {
			System.out.println("해당 상품이 존재하지 않습니다.");
		}
	}

	@Override
	public void delete(int proudctId) {
		if (store.containsKey(proudctId)) {
			store.remove(proudctId);
		}
		else {
			System.out.println("해당 상품이 존재하지 않습니다.");
		}
	}

	@Override
	public Product selectOne(int proudctId) {
		if (store.containsKey(proudctId)) {
			return store.get(proudctId);
		}
		else {
			System.out.println("해당 상품이 존재하지 않습니다.");
			return null;
		}
	}

	@Override
	public Product selectOne(String name) {
		Product product = null;
		
		for (Map.Entry<Integer, Product> entry : store.entrySet()) {
			Product val = entry.getValue();
			
			if (val.getName().equals(name)) {
				product = val;
			}
		}
		return product;
	}

	@Override
	public List<Product> selectAll() {
		List<Product> list = new ArrayList<Product>();
		
		Set<Integer> set = store.keySet();
		for (Integer key : set) {
			list.add(store.get(key));
		}
		return list;
	}
	

	
	
	

}
