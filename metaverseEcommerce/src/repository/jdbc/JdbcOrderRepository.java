package repository.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.MyTrade;
import domain.Order;
import domain.TopInfo;
import repository.inerface.OrderRepository;

public class JdbcOrderRepository extends DAO implements OrderRepository {

	/*
	 * Field
	 */
	// 싱글톤
	private static OrderRepository orderRepository = null;

	
	/*
	 * Constructor
	 */
	// 싱글톤
	private JdbcOrderRepository() {
	}

	
	/*
	 * Method
	 */
	// 싱글톤 -> 메서드로 인스턴스 생성
	public static OrderRepository getOrderRepository() {
		if (orderRepository == null) {
			orderRepository = new JdbcOrderRepository();
		}
		return orderRepository;
	}

	@Override
	public void insert(Order order) {
		try {
			connect();

			String sql = "INSERT INTO orders VALUES (orders_seq.nextval ,?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, order.getBuyerId());
			ps.setInt(2, order.getSaleId());
			ps.setInt(3, order.getOrderQuantity());
			ps.setInt(4, order.getOrderPrice());

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
	public void update(Order order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int orderId) {
		try {
			connect();

			String sql = "DELETE FROM orders WHERE order_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, orderId);

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
	public Order selectOne(int orderId) {
		Order order = null;

		try {
			connect();

			String sql = "SELECT * FROM orders WHERE order_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, orderId);

			rs = ps.executeQuery();

			if (rs.next()) {
				order = new Order();
				order.setOrderId(rs.getInt(1));
				order.setBuyerId(rs.getInt(2));
				order.setSaleId(rs.getInt(3));
				order.setOrderQuantity(rs.getInt(4));
				order.setOrderPrice(rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return order;
	}

	@Override
	public List<Order> selectAll() {
		List<Order> list = new ArrayList<Order>();

		try {
			connect();

			String sql = "SELECT * FROM orders";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getInt(1));
				order.setBuyerId(rs.getInt(2));
				order.setSaleId(rs.getInt(3));
				order.setOrderQuantity(rs.getInt(4));
				order.setOrderPrice(rs.getInt(5));

				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 내 구매내역
	@Override
	public List<MyTrade> selectMyOrders(int buyerId) {
		List<MyTrade> list = new ArrayList<MyTrade>();

		try {
			connect();

			String sql = "SELECT o.order_id, s.seller_id, m.identification, p.name, o.order_quantity, o.order_price "
						+ "FROM orders o " 
						+ "JOIN sales s ON (o.sale_id = s.sale_id) "
						+ "JOIN products p ON (s.product_id = p.product_id) "
						+ "JOIN members m ON (s.seller_id = m.member_id) " 
						+ "WHERE o.buyer_id = ? "
						+ "ORDER BY o.order_id DESC";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, buyerId);

			rs = ps.executeQuery();

			while (rs.next()) {
				MyTrade myTrade = new MyTrade();

				myTrade.setOrderId(rs.getInt(1));
				myTrade.setSellerId(rs.getInt(2));
				myTrade.setIdentification(rs.getString(3));
				myTrade.setProductName(rs.getString(4));
				myTrade.setOrderQuatity(rs.getInt(5));
				myTrade.setOrderPrice(rs.getInt(6));

				list.add(myTrade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 내 판매내역 - 주문이 이루어져야 판매가 등록이 되어서 주문 리포지토리에 있는 것임
	@Override
	public List<MyTrade> selectMySales(int sellerId) {
		List<MyTrade> list = new ArrayList<MyTrade>();

		try {
			connect();

			String sql = "SELECT s.sale_id, o.buyer_id, m.identification, p.name, o.order_quantity, o.order_price "
						+ "FROM orders o "
						+ "JOIN sales s ON (o.sale_id = s.sale_id) " 
						+ "JOIN products p ON (s.product_id = p.product_id) "
						+ "JOIN members m ON (o.buyer_id = m.member_id) " 
						+ "WHERE s.seller_id = ? "
						+ "ORDER BY s.sale_id DESC";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sellerId);

			rs = ps.executeQuery();

			while (rs.next()) {
				MyTrade myTrade = new MyTrade();

				myTrade.setSaleId(rs.getInt(1));
				myTrade.setBuyerId(rs.getInt(2));
				myTrade.setIdentification(rs.getString(3));
				myTrade.setProductName(rs.getString(4));
				myTrade.setOrderQuatity(rs.getInt(5));
				myTrade.setOrderPrice(rs.getInt(6));

				list.add(myTrade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 구매왕
	@Override
	public List<TopInfo> topBuyer() {
		List<TopInfo> list = new ArrayList<TopInfo>();
		try {
			connect();

			String sql = "SELECT m.identification, o.구매빈도수 " 
						+ "FROM members m "
						+ "JOIN (SELECT buyer_id, count(*) AS 구매빈도수 FROM orders GROUP BY buyer_id) o "
						+ "ON (m.member_id = o.buyer_id)";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				TopInfo topInfo = new TopInfo();
				
				topInfo.setIdentification(rs.getString(1));
				topInfo.setSaleOrBuyCnt(rs.getInt(2));
				
				list.add(topInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 판매왕
	@Override
	public List<TopInfo> topSeller() {
		List<TopInfo> list = new ArrayList<TopInfo>();
		
		try {
			connect();

			String sql = "SELECT m.identification, k.판매빈도수 " 
						+ "FROM members m "
						+ "JOIN (SELECT s.seller_id, count(*) AS 판매빈도수 FROM orders o "
						+ "JOIN sales s ON (o.sale_id = s.sale_id) "
						+ "GROUP BY s.seller_id) k ON (k.seller_id = m.member_id)";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				TopInfo topInfo = new TopInfo();
				
				topInfo.setIdentification(rs.getString(1));
				topInfo.setSaleOrBuyCnt(rs.getInt(2));
				
				list.add(topInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

}
