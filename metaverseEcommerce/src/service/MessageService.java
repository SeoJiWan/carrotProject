package service;

import java.util.List;

import domain.Message;
import domain.MessageInfo;
import repository.inerface.MessageRepository;

public class MessageService {

	/*
	 * Field
	 */
	private final MessageRepository messageRepository;
	

	/*
	 * Constructor
	 */
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	
	/*
	 * Method
	 */
	// 메세지 작성
	public void writeMessage(Message message) {
		messageRepository.insert(message);
	}
	
	// 메세지 수정
	public void modifyMessage(Message message) {
		messageRepository.update(message);
	}
	
	// 메세지 삭제
	public void deleteMessage(int messageId) {
		messageRepository.delete(messageId);
	}
	
	// 메세지 단건조회
	public Message findOneMessage(int messageId) {
		return messageRepository.selectOne(messageId);
	}
	
	// 메세지 전체조회
	public List<MessageInfo> findAllMessageByReceiver(int receiver_id) {
		return messageRepository.selectAllByReceiver(receiver_id);
	}
}
