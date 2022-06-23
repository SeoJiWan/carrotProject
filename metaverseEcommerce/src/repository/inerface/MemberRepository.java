package repository.inerface;

import java.util.List;

import domain.Member;

public interface MemberRepository {

	void insert(Member member);
	
	void update(Member member);
	
	void delete(int memberId);
	
	Member selectOne(int memberId);
	
//	Member selectOne(String identification);
	
	List<Member> selectAll();
	
	Member checkIdPwd(String identification, String password);
	
	Member checkIdDupl(String identification);
}
