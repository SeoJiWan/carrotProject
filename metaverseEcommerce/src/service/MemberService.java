package service;

import java.util.List;
import domain.Member;
import domain.Product;
import repository.MemberRepository;
import repository.ProductRepository;

public class MemberService {
	
	// 필드
	private final MemberRepository memberRepository;
	private final ProductRepository productRepository;

	
	// 생성자
	public MemberService(MemberRepository memberRepository, ProductRepository productRepository) {
		this.memberRepository = memberRepository;
		this.productRepository = productRepository;
	}
	
	
	// 메서드
	public void join(Member member) {
		memberRepository.save(member);
	}
	
	public Member findMember(Long id) {
		return memberRepository.findById(id);
	}
	
	public List<Member> findMembers() {
		return memberRepository.findAllMembers();
	}
	
	public void postProduct(Product product) {
		productRepository.save(product);
	}
	
	public void purchaseProduct(Product product) {
		
	}

}
