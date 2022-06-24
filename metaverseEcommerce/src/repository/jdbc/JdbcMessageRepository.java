package repository.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.Message;
import repository.inerface.MessageRepository;

public class JdbcMessageRepository extends DAO implements MessageRepository {

	/*
	 * Field
	 */
	// 싱글톤
	private static MessageRepository messageRepository = null;

	/*
	 * Constructor
	 */
	// 싱글톤
	private JdbcMessageRepository() {
	}

	/*
	 * Method
	 */
	public static MessageRepository getMessageRepository() {
		if (messageRepository == null) {
			messageRepository = new JdbcMessageRepository();
		}
		return messageRepository;
	}

	@Override
	public void insert(Message message) {
		try {
			connect();

			String sql = "INSERT INTO messages VALUES (messages_seq.nextval, ?, ?, ?, sysdate)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, message.getSenderId());
			ps.setInt(2, message.getReceiverId());
			ps.setString(3, message.getContent());

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
	public void update(Message message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int messageId) {
		try {
			connect();

			String sql = "DELETE FROM messages WHERE message_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, messageId);

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
	public Message selectOne(int messageId) {
		Message message = null;

		try {
			connect();

			String sql = "SELECT * FROM messages WHERE message_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, messageId);

			rs = ps.executeQuery();

			if (rs.next()) {
				message = new Message();
				message.setMessageId(rs.getInt(1));
				message.setSenderId(rs.getInt(2));
				message.setReceiverId(rs.getInt(3));
				message.setContent(rs.getString(4));
				message.setSendDate(rs.getDate(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return message;
	}

	@Override
	public List<Message> selectAll() {
		List<Message> list = new ArrayList<Message>();

		try {
			connect();

			String sql = "SELECT * FROM messages";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			if (rs.next()) {
				Message message = new Message();
				
				message.setMessageId(rs.getInt(1));
				message.setSenderId(rs.getInt(2));
				message.setReceiverId(rs.getInt(3));
				message.setContent(rs.getString(4));
				message.setSendDate(rs.getDate(5));
				
				list.add(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

}
