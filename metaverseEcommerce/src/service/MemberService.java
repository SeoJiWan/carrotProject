package service;

import java.util.List;
import domain.Member;
import repository.inerface.MemberRepository;

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
	public int joinMember(Member member) {
		memberRepository.insert(member);
		return member.getMemberId();
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
	
	// 로그인 - SQL 이용
	public Member logIn(String identification, String password) {
		return memberRepository.checkIdPwd(identification, password);
	}

	// 로그아웃
	public Member logOut() {
		return null;
	}
	
	// 아이디 중복확인
	public int checkIdDupl(String identification) {
		Member member = memberRepository.checkIdDupl(identification);
		
		if (member != null) {
			return 1; // 중복
		}
		else {
			return 0; // 중복x
		}
	}
}
