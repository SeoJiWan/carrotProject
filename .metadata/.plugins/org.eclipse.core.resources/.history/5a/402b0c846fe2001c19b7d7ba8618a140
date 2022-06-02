package service;

import java.util.List;

import domain.Member;
import repository.MemberRepository;

public class MemberService {
	
	// 필드
	private final MemberRepository memberRepository;

	
	// 생성자
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	
	// 메서드
	void join(Member member) {
		memberRepository.save(member);
	}
	
	Member findMember(Long id) {
		return memberRepository.findById(id);
	}
	
	List<Member> findMembers() {
		return memberRepository.findAllMembers();
	}

}
