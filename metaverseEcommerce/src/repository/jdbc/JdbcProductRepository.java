package repository.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.Product;
import repository.inerface.ProductRepository;

public class JdbcProductRepository extends DAO implements ProductRepository {

	/*
	 * Field
	 */
	// 싱글톤
	private static ProductRepository productRepository = null;
	

	/*
	 * Constructor
	 */
	// 싱글톤
	private JdbcProductRepository() {}

	/*
	 * Method
	 */
	// 싱글톤 -> 메서드로 인스턴스 생성
	public static ProductRepository getProductRepository() {
		if (productRepository == null) {
			productRepository = new JdbcProductRepository();
		}
		return productRepository;
	}

	@Override
	public void insert(Product product) {
		try {
			connect();
			
			String sql = "INSERT INTO products "
						+ "VALUES (products_seq.nextval ,?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, product.getName());
			ps.setInt(2, product.getQuantity());
			ps.setInt(3, product.getPrice());
			ps.setString(4, product.getDescription());
			ps.setString(5, product.getImage());
			
			int result = ps.executeUpdate();
			
			if (result > 0) {
				System.out.println(result + "행 삽입에 성공했습니다.");
			}
			else {
				System.out.println("삽입에 실패했습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	@Override
	public void update(int productId, int productQuantity) {
		try {
			connect();
			
			String sql = "UPDATE products SET quantity = ? WHERE product_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, productQuantity);
			ps.setInt(2, productId);
			
			
			int result = ps.executeUpdate();
			
			if (result > 0) {
				System.out.println(result + "행 수정에 성공했습니다.");
			}
			else {
				System.out.println("수정에 실패했습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	@Override
	public void delete(int productId) {
		try {
			connect();
			
			String sql = "DELETE FROM products WHERE product_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, productId);
			
			int result = ps.executeUpdate();
			
			if (result > 0) {
				System.out.println(result + "행 삭제에 성공했습니다.");
			}
			else {
				System.out.println("삭제에 실패했습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	@Override
	public Product selectOne(int productId) {
		Product product = null;
		
		try {
			connect();
			
			String sql = "SELECT * FROM products WHERE product_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, productId);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				product = new Product();
				
				product.setProductId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setQuantity(rs.getInt(3));
				product.setPrice(rs.getInt(4));
				product.setDescription(rs.getString(5));
				product.setImage(rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return product;
	}

	@Override
	public Product selectOne(String name) {
		Product product = null;
		
		try {
			connect();
			
			String sql = "SELECT * FROM products WHERE name = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				product = new Product();
				product.setProductId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setQuantity(rs.getInt(3));
				product.setPrice(rs.getInt(4));
				product.setDescription(rs.getString(5));
				product.setImage(rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return product;
	}

	@Override
	public List<Product> selectAll() {
		List<Product> list = new ArrayList<Product>();
		
		try {
			connect();
			
			String sql = "SELECT * FROM products";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setQuantity(rs.getInt(3));
				product.setPrice(rs.getInt(4));
				product.setDescription(rs.getString(5));
				product.setImage(rs.getString(6));
				
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
}
