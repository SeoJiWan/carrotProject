package domain;

public class Member {
	
	/*
	 * Field
	 */
	private int memberId;
	private String identification;
	private String password;
	private String phoneNumber;
	private String address;
//	private int role; // 0:관리자, 1:사용자

	
	/*
	 * Method
	 */
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", identification=" + identification + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + "]";
	}
	
}
