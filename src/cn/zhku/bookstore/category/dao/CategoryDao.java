package cn.zhku.bookstore.category.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;
import cn.zhku.bookstore.category.domain.Category;

public class CategoryDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public List<Category> findAll(){
		String sql = "select * from category";
		try {
			return qr.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void add(Category category) {
		
		try {
			String sql = "insert into category values(?,?)";
			qr.update(sql,category.getCid(),category.getCname());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(String cid) {
		try {
			String sql = "delete from category where cid=?";
			qr.update(sql,cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	
		

}
