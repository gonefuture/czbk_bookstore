package cn.zhku.bookstore.book.service;

import java.util.List;

import cn.zhku.bookstore.book.dao.BookDao;
import cn.zhku.bookstore.book.domain.Book;

public class BookService {
	private BookDao bookDao = new BookDao(); 
	public List<Book> findAll(){
		return bookDao.findAll();			
	}
	
	public List<Book> findByCategory(String cid){
	return bookDao.findByCategory(cid);
	}
	
	public Book load(String bid){
		return bookDao.findByBid(bid);
	}
}
