package cn.zhku.bookstore.category.web.servlet.admin;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.zhku.bookstore.category.domain.Category;
import cn.zhku.bookstore.category.service.CategoryService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminCategoryServlet
 */
@WebServlet("/AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {
	private CategoryService categoryService = new CategoryService();
	
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setAttribute("categoryList",categoryService.findAll());
		return "f:adminjsps/admin/category/lidt.jsp";
	}
	
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		category.setCid(CommonUtils.uuid());
		categoryService.add(category);
		return findAll(request,response);
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String cid = request.getParameter("cid");
		try{
		categoryService.delete(cid);
		}  catch(CategoryException e)  {
			request.setAttribute("msg", e.getMessage());
			return "f:/adminjsps/msg.jsp";
		}
		
		return findAll(request,response);
	}
	
	

}
