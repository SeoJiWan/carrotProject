package repository.inerface;

import java.util.List;

import domain.Message;

public interface MessageRepository {

	void insert(Message message);
	
	void update(Message message);
	
	void delete(int messageId);
	
	Message selectOne(int messageId);
	
	List<Message> selectAll();
}
