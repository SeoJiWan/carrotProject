package repository.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import domain.Board;
import repository.inerface.BoardRepository;

public class MemoryBoardRepository implements BoardRepository {

	/*
	 * Field
	 */
	// 싱글톤
	private static BoardRepository boardRepository = null;
	private static Map<Integer, Board> store = new HashMap<Integer, Board>();
	private static int sequence = 0;

	/*
	 * Constructor
	 */
	// 싱글톤
	private MemoryBoardRepository() {}

	/*
	 * Method
	 */
	// 싱글톤 -> 메서드로 인스턴스 생성
	public static BoardRepository getBoardRepository() {
		if (boardRepository == null) {
			boardRepository = new MemoryBoardRepository();
		}
		return boardRepository;
	} 
	
	@Override
	public void insert(Board board) {
		board.setBoardId(++sequence);
		store.put(board.getBoardId(), board);
	}

	@Override
	public void update(Board board) {
		if (store.containsKey(board.getBoardId())) {
			store.put(board.getBoardId(), board);
		}
		else {
			System.out.println("해당 게시판이 존재하지 않습니다.");
		}
	}
	
	@Override
	public void delete(int boardId) {
		if (store.containsKey(boardId)) {
			store.remove(boardId);
		}
		else {
			System.out.println("해당 게시판이 존재하지 않습니다.");
		}
	}
	
	@Override
	public Board selectOne(int boardId) {
		if (store.containsKey(boardId)) {
			return store.get(boardId);
		}
		else {
			System.out.println("해당 게시판이 존재하지 않습니다.");
			return null;
		}
	}

	@Override
	public List<Board> selectAll() {
		List<Board> list = new ArrayList<Board>();
		
		Set<Integer> set = store.keySet();
		for (Integer key : set) {
			list.add(store.get(key));
		}
		return list;
	}

}
