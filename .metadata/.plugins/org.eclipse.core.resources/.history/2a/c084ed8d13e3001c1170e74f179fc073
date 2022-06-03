package test;

import java.util.List;

import domain.Member;
import repository.MemoryMemberRepository;
import service.MemberService;

public class MemberServiceTest {
	
	public static void main(String[] args) {
		
		// 인스턴스 생성
		MemberService memberService = new MemberService(MemoryMemberRepository.getMemberRepository());
		
		// given
		Member memberA = new Member(123L, "jiwan", 1111, "범물동");
		Member memberB = new Member(1223L, "ji", 2221, "수성구");
		Member memberC = new Member(1243L, "wan", 11222, "반월당");
		
		// join Test
		memberService.join(memberA);
		memberService.join(memberB);
		memberService.join(memberC);

		// findMember Test
		Member findMember = memberService.findMember(memberA.getId());
		System.out.println(findMember.getName().equals(memberA.getName()));
		
		// findMembers Test
		List<Member> findMembers = memberService.findMembers();
		System.out.println(findMembers.size());
		
		for (int i = 0; i < findMembers.size(); i++) {
			System.out.println(findMembers.get(i));
		}
	}

}
