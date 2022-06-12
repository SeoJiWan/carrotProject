package service;

import java.util.List;

import domain.Product;
import repository.ProductRepository;

public class ProductService {
	
	/*
	 * 필드
	 */
	private ProductRepository productRepository;
	
	
	/*
	 * 생성자
	 */
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	
	/*
	 * 메서드
	 */
	// 상품 조회
	public Product findProduct(String name) {
		return productRepository.findByName(name);
	}
	
	// 상품 전체 조회
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

}
