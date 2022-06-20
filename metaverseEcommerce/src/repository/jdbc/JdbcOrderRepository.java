package repository.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

			if (rs.next()) {
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

	// 구매왕
	@Override
	public TopInfo topBuyer() {
		TopInfo topInfo = null;
		try {
			connect();

			String sql = "select m.identification, o.구매빈도수 "
						+ "from members "
						+ "join (select buyer_id, count(*) as 구매빈도수 from orders group by buyer_id) o "
						+ "on (m.member_id = o.buyer_id)";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			if (rs.next()) {
				topInfo = new TopInfo();
				topInfo.setIdentification(rs.getString(1));
				topInfo.setSaleOrBuyCnt(rs.getInt(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return topInfo;
	}

	// 판매왕
	@Override
	public TopInfo topSeller() {
		TopInfo topInfo = null;
		try {
			connect();

			String sql = "select m.identification, k.판매빈도수"
						+ "from members "
						+ "join (select s.seller_id, count(*) as 판매빈도수 from orders o "
						+ "join sales s on (o.sale_id = s. sale_id) "
						+ "group by s.seller_id) k on (k.seller_id = m.member_id)";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			if (rs.next()) {
				topInfo = new TopInfo();
				topInfo.setIdentification(rs.getString(1));
				topInfo.setSaleOrBuyCnt(rs.getInt(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return topInfo;
	}

}
