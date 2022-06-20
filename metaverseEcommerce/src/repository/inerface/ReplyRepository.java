package repository.inerface;

import java.util.List;

import domain.Reply;

public interface ReplyRepository {
	
	void insert(Reply reply);
	
	void update(Reply reply);
	
	void delete(int replyId);
	
	Reply selectOne(int replyId);
	
	List<Reply> selectAll(int boardId);

}
