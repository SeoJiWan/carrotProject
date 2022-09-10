package repository.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Member;
import repository.inerface.MemberRepository;

public class JdbcMemberRepsitory extends DAO implements MemberRepository {

	/*
	 * Field
	 */
	// 싱글톤
	private static MemberRepository memberRepsitory = null;

	/*
	 * Constructor
	 */
	// 싱글톤
	private JdbcMemberRepsitory() {}

	
	/*
	 * Method
	 */
	// 싱글톤
	public static MemberRepository getMemberRepository() {
		if (memberRepsitory == null) {
			memberRepsitory = new JdbcMemberRepsitory();
		}
		return memberRepsitory;
	}
	
	@Override
	public void insert(Member member) {
		try {
			connect();
			
			String sql = "INSERT INTO members "
						+ "VALUES (members_seq.nextval, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getIdentification());
			ps.setString(2, member.getPassword());
			ps.setString(3, member.getPhoneNumber());
			ps.setString(4, member.getAddress());
			
			int result = ps.executeUpdate();
			
			if (result > 0) {
				System.out.println(result + "행 삽입에 성공했습니다.");
			}
			else {
				System.out.println("삽입에 실패했습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 보류
	@Override
	public void update(Member member) {
			
	}

	@Override
	public void delete(int memberId) {
		try {
			connect();
			
			String sql = "DELETE FROM members WHERE member_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memberId);
			
			int result = ps.executeUpdate();
			
			if (result > 0) {
				System.out.println(result + "행 삭제에 성공했습니다.");
			}
			else {
				System.out.println("삭제에 실패했습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	@Override
	public Member selectOne(int memberId) {
		Member member = null;
		
		try {
			connect();
			
			String sql = "SELECT * FROM members WHERE member_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memberId);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				member = new Member();
				member.setMemberId(rs.getInt(1));
				member.setIdentification(rs.getString(2));
				member.setPassword(rs.getString(3));
				member.setPhoneNumber(rs.getString(4));
				member.setAddress(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return member;
	}

	@Override
	public List<Member> selectAll() {
		List<Member> list = new ArrayList<Member>();
		try {
			connect();
			
			String sql = "SELECT * FROM members";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getInt(1));
				member.setIdentification(rs.getString(2));
				member.setPassword(rs.getString(3));
				member.setPhoneNumber(rs.getString(4));
				member.setAddress(rs.getString(5));
				
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	// 로그인
	@Override
	public Member checkIdPwd(String identification, String password) {
		Member member = null;
		try {
			connect();
			
			String sql = "SELECT * FROM members WHERE identification = ? AND password = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, identification);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				member = new Member();
				
				member.setMemberId(rs.getInt(1));
				member.setIdentification(rs.getString(2));
				member.setPassword(rs.getString(3));
				member.setPhoneNumber(rs.getString(4));
				member.setAddress(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return member;
	}
	
	// 아이디 중복체크
	@Override
	public Member checkIdDupl(String identification) {
		Member member = null;
		try {
			connect();
			
			String sql = "SELECT * FROM members WHERE identification = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, identification);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				member = new Member();
				member.setMemberId(rs.getInt(1));
				member.setIdentification(rs.getString(2));
				member.setPassword(rs.getString(3));
				member.setPhoneNumber(rs.getString(4));
				member.setAddress(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return member;
	}

}
