package repository.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.Reply;
import repository.inerface.ReplyRepository;

public class JdbcReplyRepository extends DAO implements ReplyRepository {

	/*
	 * Field
	 */
	private static ReplyRepository replyRepository = null;

	/*
	 * Constructor
	 */
	private JdbcReplyRepository() {
	}

	/*
	 * Method
	 */
	public ReplyRepository getReplyRepository() {
		if (replyRepository == null) {
			replyRepository = new JdbcReplyRepository();
		}
		return replyRepository;
	}

	@Override
	public void insert(Reply reply) {
		try {
			connect();

			String sql = "INSERT INTO replys " + "VALUES (replys_seq.nextval, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(2, reply.getBoardId());
			ps.setInt(3, reply.getMemberId());
			ps.setString(4, reply.getContent());
			ps.setDate(5, reply.getRegDate());

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
	public void update(Reply reply) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int replyId) {
		try {
			connect();

			String sql = "DELETE FROM replys WHERE reply_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, replyId);

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
	public Reply selectOne(int replyId) {
		Reply reply = null;

		try {
			connect();

			String sql = "SELECT * FROM replys WHERE reply_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, replyId);

			rs = ps.executeQuery();

			if (rs.next()) {
				reply = new Reply();
				
				reply.setReplyId(rs.getInt(1));
				reply.setBoardId(rs.getInt(2));
				reply.setMemberId(rs.getInt(3));
				reply.setContent(rs.getString(4));
				reply.setRegDate(rs.getDate(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return reply;
	}

	@Override
	public List<Reply> selectAll(int boardId) {
		List<Reply> list = new ArrayList<Reply>();

		try {
			connect();

			String sql = "SELECT * FROM replys";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			if (rs.next()) {
				Reply reply = new Reply();
				
				reply.setReplyId(rs.getInt(1));
				reply.setBoardId(rs.getInt(2));
				reply.setMemberId(rs.getInt(3));
				reply.setContent(rs.getString(4));
				reply.setRegDate(rs.getDate(5));
				
				list.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

}
