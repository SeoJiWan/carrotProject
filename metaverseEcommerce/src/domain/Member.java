package domain;

public class Member {
	
	// 필드
	private Long id;
	private String name;
	private int phoneNum;
	private String address;
	
	
	// 생성자
	public Member(Long id, String name, int phoneNum, String address) {
		this.id = id;
		this.name = name;
		this.phoneNum = phoneNum;
		this.address = address;
	}

	
	// 메서드
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
