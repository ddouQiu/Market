package com.netease.web.Service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netease.web.Dao.ProductDao;
import com.netease.web.meta.Product;

public class ProductService {
	private ProductDao productDao=null;
	private ApplicationContext ac=null;
	private SqlSessionFactory sqlSessionFactory=null;
	private SqlSession session=null;
	
	public ProductService(){
		ac=new ClassPathXmlApplicationContext("application-context.xml");
		productDao=ac.getBean("productDao", ProductDao.class);
		sqlSessionFactory=ac.getBean("sqlSessionfactory",SqlSessionFactory.class);
		session=sqlSessionFactory.openSession();
	}
	public SqlSession GetSession(){
		return session;
	}
	
	public void AddProduct(Product product){
		productDao.insert(product);
	}

	public List<Product> GetAllProduct(){
		return productDao.selectAll();
	}
	
	public void DeleteSpecifiedProductByName(String productName){
		productDao.deletSpecifiedProduct(productName);
	}
	
	public Product CheckSpecifiedProduct(String productName){
        return productDao.CheckSpecifiedProduct(productName);		
	}
	
	public void updateSpecificProduct(Product product){
		//System.out.println(product.getId());
		productDao.UpdateSpecificProduct(product);
	}
	
	public void UpdateSellNum(int productId,int sellnum){
		productDao.updateSellNum(sellnum, productId);
	}
	
	public boolean hasBuied(int productId, int buyerId){
		int num=0;
		if((num=productDao.hasBuied(productId, buyerId))>0){
			System.out.println(num);
			return true;
		}
		return false;
	}
	
	public List<Product> getUnBuiedProducts(int buyerId){
		return productDao.getNotBuiedProducts(buyerId);
	}
}
