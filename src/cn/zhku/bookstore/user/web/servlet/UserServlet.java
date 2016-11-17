package cn.zhku.bookstore.user.web.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.servlet.BaseServlet;
import cn.zhku.bookstore.cart.domain.Cart;
import cn.zhku.bookstore.user.domain.User;
import cn.zhku.bookstore.user.service.UserException;
import cn.zhku.bookstore.user.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	
	UserService userService = new UserService();
	public String regist(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		User form =  CommonUtils.toBean(request.getParameterMap(),User.class);
		
		form.setUid(CommonUtils.uuid());
		form.setCode(CommonUtils.uuid()+CommonUtils.uuid());
		
		Map<String,String> errors  = new HashMap<String ,String>();
		
		String username = form.getUsername();
		if(username == null || username.trim().isEmpty()){
			errors.put("username", "用户名不能空！");			
		}else if(username.length() > 3 || username.length() > 10){
			errors.put("username", "用户名长度必须在3~10之间");
		}
		
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()){
			errors.put("password", "密码不能空！");			
		}else if(username.length() > 3 || username.length() > 10){
			errors.put("username", "密码长度必须在3~10之间");
		}
		
		String email = form.getEmail();
		if(email == null || email.trim().isEmpty()){
			errors.put("email", "邮箱不能空！");			
		}else if(!email.matches("\\w+@\\w+\\.\\w+")){
			errors.put("email", "Email格式错误！");
		}
		
		if(errors.size() > 0){
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		
		try{
			userService.regist(form);
		
		} catch (UserException e){
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		
		/*Properties props = new Properties();
		props.load(this.getClass().getClassLoader().
				getResourceAsStream("email_template.properties"));
		String host = props.getProperty("host");
		String uname = props.getProperty("uname");
		String pwd = props.getProperty("pwd");
		String from = props.getProperty("from");
		String to = form.getEmail();
		String subject = props.getProperty("subject");
		String content = props.getProperty("content");
		content =MessageFormat.format(content, form.getCode());
		Session session = MailUtils.createSession(host, uname, pwd);
		Mail mail = new Mail(from,to,subject,content);
		try{
			MailUtils.send(session, mail);
		} catch(MessagingException e){
			throw new RuntimeException(e);
			
		}*/
		request.setAttribute("msg", "恭喜，注册成功！请马上到邮箱激活");
		return "f:/jsps/msg.jsp";
	}

	public String active(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		String code = request.getParameter("code");
		try{
			userService.active(code); 
			request.setAttribute("msg","恭喜，您激活成功了！请马上登录！");
		} catch(UserException e){
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/jsps/msg.jsp";
		
	}
	
	public String login(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
		
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		try {
			User user = userService.login(form);
			request.getSession().setAttribute("session_user", user);
			
			request.getSession().setAttribute("cart", new Cart());
			
			return "r:/index.jsp";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/jsps/user/login.jsp";
		}	
		
	}
	
	public String quit(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		request.getSession().invalidate();
		return "r:/index.jsp";
		
	}
	
	
	
	
}
