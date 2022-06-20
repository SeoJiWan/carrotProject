package test;

import java.util.Date;
import java.util.List;

import domain.Board;
import repository.memory.MemoryBoardRepository;
import service.BoardService;

public class BoardTest {
	
	public static void main(String[] args) {
		
		BoardService boardService = new BoardService(MemoryBoardRepository.getBoardRepository());
		
		// board 객체 생성
		Board board= new Board();
		board.setBoardId(1);
		board.setMemberId(1);
		board.setTitle("ㅎㅇ");
		board.setContent(" 반갑습니다.");
		board.setRegDate(new Date());
		
		// 게시글 작성
		boardService.writeBoard(board);
		
		// board 객체 생성 - 수정용
		Board board1= new Board();
		board1.setBoardId(2);
		board1.setMemberId(1);
		board1.setTitle("ㅂㅂ");
		board1.setContent("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
		board1.setRegDate(new Date());
		
		// 게시글 수정
		boardService.modifyBoard(board1);
		
		// 게시글 삭제
		boardService.deleteBoard(1);
		
		List<Board> showAllBoards = boardService.findAllBoards();
		showAllBoards.forEach(System.out::println);
		
	}

}
