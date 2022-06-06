package service;

import java.util.List;
import domain.Member;
import domain.Product;
import repository.MemberRepository;
import repository.ProductRepository;

public class MemberService {
	
	/*
	 * 필드
	 */
	private final MemberRepository memberRepository;

	
	/*
	 * 생성자
	 */
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	
	/*
	 * 메서드
	 */
	// 회원가입
	public void join(Member member) {
		memberRepository.save(member);
	}
	
	// 단일 회원조회
	public Member findMember(Long id) {
		return memberRepository.findById(id);
	}
	
	// 전체 회원조회
	public List<Member> findAllMembers() {
		return memberRepository.findAll();
	}
}
