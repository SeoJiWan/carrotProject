package repository.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.Board;
import repository.inerface.BoardRepository;

public class JdbcBoardRepository extends DAO implements BoardRepository{

	/*
	 * Field
	 */
	// 싱글톤
	private static BoardRepository boardRepository = null;


	/*
	 * Constructor
	 */
	// 싱글톤
	private JdbcBoardRepository() {}

	/*
	 * Method
	 */
	// 싱글톤 -> 메서드로 인스턴스 생성
	public static BoardRepository getBoardRepository() {
		if (boardRepository == null) {
			boardRepository = new JdbcBoardRepository();
		}
		return boardRepository;
	}

	@Override
	public void insert(Board board) {
		try {
			connect();

			String sql = "INSERT INTO boards VALUES (boards_seq.nextval ,?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(2, board.getMemberId());
			ps.setString(3, board.getTitle());
			ps.setString(4, board.getContent());
			ps.setDate(5, board.getRegDate());

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
	public void update(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int boardId) {
		try {
			connect();

			String sql = "DELETE FROM boards WHERE board_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardId);

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
	public Board selectOne(int boardId) {
		Board board = null;

		try {
			connect();

			String sql = "SELECT * FROM boards WHERE boards_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardId);

			rs = ps.executeQuery();

			if (rs.next()) {
				board = new Board();
				board.setBoardId(rs.getInt(1));
				board.setMemberId(rs.getInt(2));
				board.setTitle(rs.getString(3));
				board.setContent(rs.getString(4));
				board.setRegDate(rs.getDate(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return board;
	}

	@Override
	public List<Board> selectAll() {
		List<Board> list = new ArrayList<Board>();

		try {
			connect();

			String sql = "SELECT * FROM boards";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			if (rs.next()) {
				Board board = new Board();
				board.setBoardId(rs.getInt(1));
				board.setMemberId(rs.getInt(2));
				board.setTitle(rs.getString(3));
				board.setContent(rs.getString(4));
				board.setRegDate(rs.getDate(4));
				
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
}
