package repository.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import domain.Reply;
import repository.inerface.ReplyRepository;

public class MemoryReplyRepository implements ReplyRepository {

	/*
	 * Field
	 */
	// 싱글톤
	private static ReplyRepository replyRepository = null;
	private static Map<Integer, Reply> store = new HashMap<Integer, Reply>();
	private static int sequence = 0;
	

	/*
	 * Constructor
	 */
	// 싱글톤
	private MemoryReplyRepository() {}

	/*
	 * Method
	 */
	// 싱글톤 -> 메서드로 인스턴스 생성
	public static ReplyRepository getrReplyRepository() {
		if (replyRepository == null) {
			replyRepository = new MemoryReplyRepository();
		}
		return replyRepository;
	}

	@Override
	public void insert(Reply reply) {
		reply.setReplyId(++sequence);
		store.put(reply.getReplyId(), reply);
	}

	@Override
	public void update(Reply reply) {
		if (store.containsKey(reply.getReplyId())) {
			store.put(reply.getReplyId(), reply);
		}
		else {
			System.out.println("해당 댓글이 존재하지 않습니다.");
		}
	}

	@Override
	public void delete(int replyId) {
		if (store.containsKey(replyId)) {
			store.remove(replyId);
		}
		else {
			System.out.println("해당 댓글이 존재하지 않습니다.");
		}
	}

	@Override
	public Reply selectOne(int replyId) {
		if (store.containsKey(replyId)) {
			return store.get(replyId);
		}
		else {
			System.out.println("해당 댓글이 존재하지 않습니다.");
			return null;
		}
	}

	@Override
	public List<Reply> selectAll(int boardId) {
		List<Reply> list = new ArrayList<Reply>();
		for (Map.Entry<Integer, Reply> entry : store.entrySet()) {
			Reply val = entry.getValue();
			
			if (val.getBoardId() == boardId) {
				list.add(val);
			}
		}
		return list;
	}

}
