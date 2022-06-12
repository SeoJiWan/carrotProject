package repository;

import java.util.HashMap;
import java.util.Map;
import domain.Post;

public class MemoryPostRepository implements PostRepository{

	/*
	 * 필드
	 */
	private static MemoryPostRepository memoryPostRepository = new MemoryPostRepository();
	private static Map<Long, Post> store = new HashMap<Long, Post>();
	
	
	/*
	 * 메서드
	 */
	public static MemoryPostRepository getMemoryPostRepository() {
		return memoryPostRepository;
	}

	@Override
	public void save(Post post) {
		store.put(post.getOrderId(), post);
	}

	@Override
	public Post findById(Long id) {
		return store.get(id);
	}
	
	
}
