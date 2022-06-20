package service;

import java.util.List;

import domain.Board;
import repository.inerface.BoardRepository;

public class BoardService {

	/*
	 * Field
	 */
	private final BoardRepository boardRepository;

	
	/*
	 * Constructor
	 */
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	
	/*
	 * Method
	 */
	// 게시글 작성
	public void writeBoard(Board board) {
		boardRepository.insert(board);
	}
	
	// 게시글 수정
	public void modifyBoard(Board board) {
		boardRepository.update(board);
	}
	
	// 게시글 삭제
	public void deleteBoard(int boardId) {
		boardRepository.delete(boardId);
	}
	
	// 게시글 단건조회
	public Board findOneBoard(int boardId) {
		return boardRepository.selectOne(boardId);
	}
	
	// 게시글 전체조회
	public List<Board> findAllBoards() {
		return boardRepository.selectAll();
	}
}
