package repository.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.Sale;
import domain.SaleInfo;
import repository.inerface.SaleRepository;

public class JdbcSaleRepository extends DAO implements SaleRepository{
	
	/*
	 * Field
	 */
	// 싱글톤
	private static SaleRepository saleRepository = null;
		

	/*
	 * Constructor
	 */
	// 싱글톤
	private JdbcSaleRepository() {}

	
	/*
	 * Method
	 */
	// 싱글톤 -> 메서드로 인스턴스 생성
	public static SaleRepository getSaleRepository() {
		if (saleRepository == null) {
			saleRepository = new JdbcSaleRepository();
		}
		return saleRepository;
	}

	@Override
	public void insert(Sale sale) {
		try {
			connect();

			String sql = "INSERT INTO sales VALUES (sales_seq.nextval ,?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sale.getSellerId());
			ps.setInt(2, sale.getProductId());
			ps.setString(3, sale.getSaleStatus());

			int result = ps.executeUpdate();

			if (result > 0) {
				System.out.println(result + "행 삽입에 성공했습니다.");
			} else {
				System.out.println("삽입에 실패했습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	@Override
	public void update(Sale sale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int saleId) {
		try {
			connect();

			String sql = "DELETE FROM sales WHERE sale_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, saleId);

			int result = ps.executeUpdate();

			if (result > 0) {
				System.out.println(result + "행 삭제에 성공했습니다.");
			} else {
				System.out.println("삭제에 실패했습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
	}

	@Override
	public Sale selectOne(int saleId) {
		Sale sale = null;

		try {
			connect();

			String sql = "SELECT * FROM sales WHERE sale_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, saleId);

			rs = ps.executeQuery();

			if (rs.next()) {
				sale = new Sale();
				sale.setSaleId(rs.getInt(1));
				sale.setSellerId(rs.getInt(2));
				sale.setProductId(rs.getInt(3));
				sale.setSaleStatus(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return sale;
	}

	@Override
	public List<SaleInfo> selectAll() {
		List<SaleInfo> list = new ArrayList<SaleInfo>();

		try {
			connect();

			String sql = "SELECT s.sale_id, s.seller_id, m.identification, s.sale_status, p.name, p.quantity, p.price, p.description, m.address "
						+ "FROM sales s "
						+ "JOIN products p ON (s.product_id = p.product_id) "
						+ "JOIN members m ON (s.seller_id = m.member_id)";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				SaleInfo saleInfo = new SaleInfo();
				saleInfo.setSaleId(rs.getInt(1));
				saleInfo.setSellerId(rs.getInt(2));
				saleInfo.setIdentification(rs.getString(3));
				saleInfo.setSaleStatus(rs.getString(4));
				saleInfo.setProductName(rs.getString(5));
				saleInfo.setProductQuantity(rs.getInt(6));
				saleInfo.setProductPrice(rs.getInt(7));
				saleInfo.setProductDescription(rs.getString(8));
				saleInfo.setAddress(rs.getString(9));
				
				list.add(saleInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
}
