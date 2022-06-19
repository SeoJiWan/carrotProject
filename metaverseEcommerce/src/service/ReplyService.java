package service;

import java.util.List;

import domain.Reply;
import repository.MemoryReplyRepository;
import repository.ReplyRepository;

public class ReplyService {
	
	/*
	 * Field
	 */
	private final ReplyRepository replyRepository;

	
	/*
	 * Constructor
	 */
	public ReplyService(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
	}
	

	/*
	 * Method
	 */
	// 댓글 작성
	public void writeReply(Reply reply) {
		replyRepository.insert(reply);
	}
	
	// 댓글 수정
	public void modifyReply(Reply reply) {
		replyRepository.update(reply);
	}
	
	// 댓글 삭제
	public void deleteReply(int replyId) {
		replyRepository.delete(replyId);
	}
	
	// 댓글 단건조회
	public Reply findOneReply(int replyId) {
		return replyRepository.selectOne(replyId);
	}
	
	// 특정 게시글의 댓글 전체조회
	public List<Reply> findAllReply(int boardId) {
		return replyRepository.selectAll(boardId);
	}
	
//	// 모든 댓글 조회 - 테스트용
//	public int allReplysSize() {
//		return MemoryReplyRepository.store.size();
//	}
}
