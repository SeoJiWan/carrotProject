package service;

import domain.Member;
import domain.Post;
import domain.Product;
import repository.MemberRepository;
import repository.MemoryMemberRepository;
import repository.MemoryProductRepository;
import repository.PostRepository;
import repository.ProductRepository;

public class PostService {

	/*
	 * 필드
	 */
	private PostRepository postRepository;
	private MemberRepository memberRepository = MemoryMemberRepository.getMemberRepository();
	private ProductRepository productRepository = MemoryProductRepository.getMemoryProductRepository();
	
	
	/*
	 * 생성자
	 */
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	
	/*
	 * 메서드
	 */
	// 상품 등록
	public void post(Long memberId, Product product, int count) {
		Member member = memberRepository.findById(memberId);
		
		Post post = createPost(member, product, product.getPrice(), count);
		postRepository.save(post);
		member.addPost(post);
	}
	
	// 상품 등록 내역 생성
	public Post createPost(Member member, Product product, int postPrice, int count) {
		Post post = new Post();
		post.setMember(member);
		post.setProduct(product);
		post.setpostPrice(postPrice);
		post.setCount(count);
		
		product.setSeller(member.getName());
		
		productRepository.save(product);
		
		return post;
	}
	
	// 상품 등록 내역 확인
	public Post findPost(Long id) {
		return postRepository.findById(id);
	}
}
