package domain;

import java.util.ArrayList;
import java.util.List;

public class Member {
	
	// 필드
	private Long id;
	private String name;
	private int phoneNum;
	private String address;
	private List<Order> orders = new ArrayList<>();

	
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
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}

	@Override
	public String toString() {
		return "Member [id = " + id + ", name = " + name + ", phoneNum = " + phoneNum + ", address = " + address + "]";
	}
}
