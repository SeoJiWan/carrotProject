package service;

import java.util.List;

import domain.Product;
import repository.inerface.ProductRepository;

public class ProductService {

	/*
	 * Field
	 */
	private final ProductRepository productRepository;

	/*
	 * Constructor
	 */
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/*
	 * Method
	 */
	// 상품 등록
	public void saveProduct(Product product) {
		productRepository.insert(product);
	}

	// 상품 수정
	public void modifyProduct(int productId, int productQuantity) {
		productRepository.update(productId, productQuantity);
	}

	// 상품 삭제
	public void deleteProduct(int productId) {
		productRepository.delete(productId);
	}

	// 상품 단건조회 - 상품번호
	public Product findOneProductById(int productId) {
		return productRepository.selectOne(productId);
	}

	// 상품 단건조회 - 상품이름
	public Product findOneProductById(String name) {
		return productRepository.selectOne(name);
	}
	
	// 전체 상품조회
	public List<Product> findAllProducts() {
		return productRepository.selectAll();
	}

}
