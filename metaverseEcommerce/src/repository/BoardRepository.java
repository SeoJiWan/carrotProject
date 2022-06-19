package repository;

import java.util.List;

import domain.Board;

public interface BoardRepository {

	void insert(Board board);
	
	void update(Board board);

	void delete(int boardId);

	Board selectOne(int boardId);

	List<Board> selectAll();
}
