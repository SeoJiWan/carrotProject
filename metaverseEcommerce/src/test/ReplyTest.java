package test;

import java.util.Date;
import java.util.List;

import domain.Reply;
import repository.MemoryReplyRepository;
import service.ReplyService;

public class ReplyTest {
	
	public static void main(String[] args) {
		
		ReplyService replyService = new ReplyService(MemoryReplyRepository.getrReplyRepository());
		
		// 댓글 인스턴스 생성
		Reply reply = new Reply();
		reply.setReplyId(1);
		reply.setBoardId(1);
		reply.setMemeberId(1);
		reply.setContent("thank you");
		reply.setRegDate(new Date());

		Reply reply1 = new Reply();
		reply1.setReplyId(2);
		reply1.setBoardId(1);
		reply1.setMemeberId(1);
		reply1.setContent("goot");
		reply1.setRegDate(new Date());
		
		Reply reply2 = new Reply();
		reply2.setReplyId(3);
		reply2.setBoardId(2);
		reply2.setMemeberId(3);
		reply2.setContent("sorry");
		reply2.setRegDate(new Date());
		// 수정용
		Reply reply3 = new Reply();
		reply3.setReplyId(3);
		reply3.setBoardId(2);
		reply3.setMemeberId(3);
		reply3.setContent("wow");
		reply3.setRegDate(new Date());
		
		// 게시글 작성
		replyService.writeReply(reply);
		replyService.writeReply(reply1);
		replyService.writeReply(reply2);
		
		// 댓글 수 조회
//		int allReplysSize = replyService.allReplysSize();
//		System.out.println("allReplysSize = " + allReplysSize);
		
		// 댓글 수정
		replyService.modifyReply(reply3);
		
		// 댓글 삭제
		replyService.deleteReply(1);
		
		// 특정 게시글의 댓글 전체조회
		List<Reply> showAllReply = replyService.findAllReply(1);
		showAllReply.forEach(System.out::println);
		
		
		
		
	}

}
