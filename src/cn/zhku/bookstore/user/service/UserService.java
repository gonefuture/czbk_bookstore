package cn.zhku.bookstore.user.service;

import cn.zhku.bookstore.user.dao.UserDao;
import cn.zhku.bookstore.user.domain.User;

public class UserService {
	private UserDao userDao = new UserDao();
	
	public void regist(User form) throws UserException{
		User user =  userDao.findByUsername(form.getUsername());
		if(user != null) throw new UserException("用户名已被注册！");
		
		user = userDao.findByEmail(form.getEmail());; 
		if(user != null) throw new UserException("Email已经被注册！");
		
		userDao.add(form);
	}
	
	public void active(String code) throws UserException{
		User user = userDao.findByCode(code);
		
		if(user == null) throw new UserException("激活码无效");	
		
		if(user.isState()) throw new UserException("您已经激活过了，不要再激活了，除非你想死！");
		
		userDao.updataState(user.getUid(), true);
	}
	
public User login(User form) throws UserException{
		
		User user = userDao.findByUsername(form.getUsername());
		if(user == null) throw new UserException("用户名不存在！");
		if(!user.getPassword().equals(form.getPassword()))
			throw new UserException("密码错误");
		if(!user.isState()) throw new UserException("尚未激活！");
		return user;
	}
	

	

}
