package repository;

import java.util.List;
import domain.Member;

public interface MemberRepository {

	void save(Member member);
	
	Member findById(Long id);
	
	List<Member> findAllMembers();
}
