package cn.zhku.bookstore.order.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.zhku.bookstore.cart.domain.Cart;
import cn.zhku.bookstore.cart.domain.CartItem;
import cn.zhku.bookstore.order.domain.Order;
import cn.zhku.bookstore.order.domain.OrderItem;
import cn.zhku.bookstore.order.service.OrderException;
import cn.zhku.bookstore.order.service.OrderService;
import cn.zhku.bookstore.user.domain.User;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	
	OrderService orderService = new OrderService();
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		Cart cart= (Cart)	request.getSession().getAttribute("cart");
		Order order = new Order();
		order.setOid(CommonUtils.uuid());
		order.setOrdertime(new Date());
		order.setState(1);
		User user =(User) request.getSession().getAttribute("session_user");
		order.setOwer(user);
		order.setTotal(cart.getTotal());
		
		List<OrderItem> orderItemList =  new  ArrayList<OrderItem>();
		for(CartItem cartItem : cart.getCartItems()){
			OrderItem oi = new OrderItem();
			oi.setIid(CommonUtils.uuid());
			oi.setSubtotal(cartItem.getSubtotal());
			oi.setOrder(order);
			orderItemList.add(oi);
		}
		
		order.setOrderItemList(orderItemList);
		cart.clear();
		orderService.add(order);
		
		return "f:jsps/order/desc.jsp";			
	} 
	
	public String myOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		User user = (User) request.getSession().getAttribute("session_user");
		List<Order> orderList = orderService.myOrders(user.getUid());
		request.setAttribute("orderList", orderList);
		return "f:jsps/order/list.jsp";		
	}
	
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setAttribute("order", orderService.load(request.getParameter("oid")));
		return "f:jsps/order/desc.jsp";		
	}
	
	public String confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String oid =request.getParameter("oid");
		try {
			orderService.confirm(oid);
			request.setAttribute("msg", "恭喜，交易成功");
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "f:jsps/msg.jsp";		
	}
}











