package service;

import java.util.List;
import domain.Member;
import repository.MemberRepository;

public class MemberService {
	
	/*
	 * Field
	 */
	private final MemberRepository memberRepository;

	
	/*
	 * Constructor
	 */
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	
	/*
	 * Method
	 */
	// 회원가입
	public void joinMember(Member member) {
		memberRepository.insert(member);
	}
	
	// 회원수정
	public void modifyMember(Member member) {
		memberRepository.update(member);
	}
	
	// 회원삭제
	public void deleteMember(int memberId) {
		memberRepository.delete(memberId);
	}
	
	// 단일 회원조회
	public Member findOneMember(int memberId) {
		return memberRepository.selectOne(memberId);
	}
	
	// 전체 회원조회
	public List<Member> findAllMembers() {
		return memberRepository.selectAll();
	}
	
	// 로그인
	public Member logIn(String identification, String password) {
		Member logInMember = null;
		
		List<Member> list = findAllMembers();
		
		for (Member member : list) {
			if (member.getIdentification().equals(identification) 
					&& member.getPassword().equals(password)) {
				logInMember = member;
				System.out.println(identification + " 님, 로그인 하였습니다.");
				return logInMember;
			}
		}
		System.out.println("아이디 또는 비밀번호를 잘못 입력했습니다.");
		return null;
		
	}
}
