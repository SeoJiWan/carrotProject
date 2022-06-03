package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Member;

public class MemoryMemberRepository implements MemberRepository{

	// 필드
	private static MemberRepository memberRepository = new MemoryMemberRepository();
	private static Map<Long, Member> store = new HashMap<Long, Member>();
	
	
	// 생성자
	private MemoryMemberRepository() {
		
	}
	
	
	// 메서드
	public static MemberRepository getMemberRepository() {
		return memberRepository;
	}
	
	@Override
	public void save(Member member) {
		store.put(member.getId(), member);
	}

	@Override
	public Member findById(Long id) {
		return store.get(id);
	}

	@Override
	public List<Member> findAllMembers() {
		List<Member> list = new ArrayList<Member>();
		for (int i = 0; i < store.size(); i++) {
			list.add(store.get(i));
		}
		return list;
	}
}
