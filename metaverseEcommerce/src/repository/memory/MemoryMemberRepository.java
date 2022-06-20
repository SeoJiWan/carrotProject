package repository.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import domain.Member;
import repository.inerface.MemberRepository;

public class MemoryMemberRepository implements MemberRepository {

	/*
	 * Field
	 */
	// 싱글톤
	private static MemberRepository memberRepository = null;
	private static Map<Integer, Member> store = new HashMap<Integer, Member>();
	private static int sequence = 0;

	
	/*
	 * Constructor
	 */
	// 싱글톤
	private MemoryMemberRepository() {}

	
	/*
	 * Method
	 */
	// 싱글톤 -> 메서드로 인스턴스 생성
	public static MemberRepository getMemberRepository() {
		if (memberRepository == null) {
			memberRepository = new MemoryMemberRepository();
		}
		return memberRepository;
	}

	@Override
	public void insert(Member member) {
		member.setMemberId(++sequence);
		store.put(member.getMemberId(), member);
	}

	@Override
	public void update(Member member) {
		if (store.containsKey(member.getMemberId())) {
			store.put(member.getMemberId(), member);
		}
		else {
			System.out.println("해당 유저가 존재하지 않습니다.");
		}
	}

	@Override
	public void delete(int memberId) {
		if (store.containsKey(memberId)) {
			store.remove(memberId);
		} else {
			System.out.println("해당 유저가 존재하지 않습니다.");
		}
	}

	@Override
	public Member selectOne(int memberId) {
		if (store.containsKey(memberId)) {
			return store.get(memberId);
		} else {
			System.out.println("해당 유저가 존재하지 않습니다.");
			return null;
		}
	}

	@Override
	public List<Member> selectAll() {
		List<Member> list = new ArrayList<Member>();

		Set<Integer> set = store.keySet();
		for (Integer key : set) {
			list.add(store.get(key));
		}
		return list;
	}


	@Override
	public Member checkIdPwd(String identification, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
