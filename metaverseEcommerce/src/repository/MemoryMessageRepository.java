package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import domain.Message;

public class MemoryMessageRepository implements MessageRepository{

	/*
	 * Field
	 */
	// 싱글톤
	private static MessageRepository messageRepository = null;
	private static Map<Integer, Message> store = new HashMap<Integer, Message>();
	private static int sequence = 0;

	
	/*
	 * Constructor
	 */
	// 싱글톤
	private MemoryMessageRepository() {}
	

	/*
	 * Method
	 */
	// 싱글톤 -> 메서드로 인스턴스 생성
	public static MessageRepository getMessageRepository() {
		if (messageRepository == null) {
			messageRepository = new MemoryMessageRepository();
		}
		return messageRepository;
	}

	@Override
	public void insert(Message message) {
		message.setReceiverId(++sequence);
		store.put(message.getMessageId(), message);
	}

	@Override
	public void update(Message message) {
		if (store.containsKey(message.getMessageId())) {
			store.put(message.getMessageId(), message);
		}
		else {
			System.out.println("해당 메세지가 존재하지 않습니다.");
		}
	}

	@Override
	public void delete(int messageId) {
		if (store.containsKey(messageId)) {
			store.remove(messageId);
		} else {
			System.out.println("해당 메세지가 존재하지 않습니다.");
		}
	}

	@Override
	public Message selectOne(int messageId) {
		if (store.containsKey(messageId)) {
			return store.get(messageId);
		} else {
			System.out.println("해당 메세지가 존재하지 않습니다.");
			return null;
		}
	}

	@Override
	public List<Message> selectAll() {
		List<Message> list = new ArrayList<Message>();

		Set<Integer> set = store.keySet();
		for (Integer key : set) {
			list.add(store.get(key));
		}
		return list;
	}
}
