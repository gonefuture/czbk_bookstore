package cn.zhku.bookstore.order.domain;

import java.util.Date;
import java.util.List;

import cn.zhku.bookstore.user.domain.User;

public class Order {
	private String oid;
	private Date ordertime;
	private double total;
	private int state;
	private User Ower;
	
	private List<OrderItem>  orderItemList ;
	
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	private String address;
	
	
	
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public User getOwer() {
		return Ower;
	}
	public void setOwer(User ower) {
		Ower = ower;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", total="
				+ total + ", state=" + state + ", Ower=" + Ower + ", address="
				+ address + "]";
	}
	
	
	
	  
	
	
}
