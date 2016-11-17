package cn.zhku.bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.jdbc.JdbcUtils;
import cn.zhku.bookstore.order.dao.OrderDao;
import cn.zhku.bookstore.order.domain.Order;

public class OrderService {
	OrderDao orderDao = new OrderDao();
	
	/*
	 * 处理事务
	 */
	
	public void add(Order order) {
		try{
			JdbcUtils.beginTransaction();
			orderDao.addOrder(order);
			orderDao.addOrderItemList(order.getOrderItemList());
			
			JdbcUtils.commitTransaction();
			
		} catch(Exception e){
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				
			}
			throw new RuntimeException(e);
		}
		
	}

	public List<Order> myOrders(String uid) {
		return orderDao.findByUid(uid); 
	}

	public Order load(String oid) {
		
		return  orderDao.load(oid);
	}
	
	public void confirm(String oid) throws OrderException{
		int state = orderDao.getStateByOid(oid);
		if(state != 3) throw new OrderException("订单失败");
	
		orderDao.updateState(oid, 4);
		
	}
	
	

}
