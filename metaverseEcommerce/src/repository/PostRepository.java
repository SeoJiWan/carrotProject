package repository;

import domain.Post;

public interface PostRepository {

	void save(Post post);

	Post findById(Long id);
}
