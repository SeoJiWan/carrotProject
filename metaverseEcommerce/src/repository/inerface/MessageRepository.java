package repository.inerface;

import java.util.List;

import domain.Message;
import domain.MessageInfo;

public interface MessageRepository {

	void insert(Message message);
	
	void update(Message message);
	
	void delete(int messageId);
	
	Message selectOne(int messageId);
	
	List<MessageInfo> selectAllByReceiver(int receiver_id);
}
