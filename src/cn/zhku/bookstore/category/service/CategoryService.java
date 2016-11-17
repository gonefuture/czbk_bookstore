package cn.zhku.bookstore.category.service;

import java.util.List;

import cn.zhku.bookstore.book.dao.BookDao;
import cn.zhku.bookstore.category.dao.CategoryDao;
import cn.zhku.bookstore.category.domain.Category;
import cn.zhku.bookstore.category.web.servlet.admin.CategoryException;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	private BookDao bookDao = new BookDao();  

	public List<Category>  findAll(){
		return categoryDao.findAll();
	}

	public void add(Category category) {
		categoryDao.add(category);
		
	}

	 public void delete(String cid) throws CategoryException {
		int count =bookDao.getCountByCid(cid);
		if(count > 0) throw new CategoryException("该分类下还有图书，不能删除");
		categoryDao.delete(cid);
	}
}
