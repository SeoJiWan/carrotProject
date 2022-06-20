package test;

import domain.Member;
import repository.jdbc.DAO;
import repository.memory.MemoryMemberRepository;
import service.BoardService;
import service.MemberService;
import service.ProductService;

public class TotalTest {

	private MemberService memberService = new MemberService(MemoryMemberRepository.getMemberRepository());
	private ProductService productService;
	private BoardService boardService;
	
	
	// 회원가입
	public void join(Member member) {
		memberService.joinMember(member);
	}
	
	// 회원조회
	public Member findMember(int memberNO) {
		return memberService.findOneMember(memberNO);
	}
	
	// 로그인
	public Member logIn() {
		return memberService.logIn("wana", "1234");
	}
	
	
	public static void main(String[] args) {
		
		TotalTest t = new TotalTest();
		
		Member member = new Member();
		member.setMemberId(1);
		member.setIdentification("wana");
		member.setPassword("1234");
		member.setAddress("범물동");
		
		Member member1 = new Member();
		member1.setMemberId(2);
		member1.setIdentification("zalq");
		member1.setPassword("50-9");
		
		t.join(member);
		
		Member logIn = t.logIn();
		System.out.println(logIn);
		
		
		
		
		
		
	}
	
	
}
