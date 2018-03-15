package com.netease.web.Service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netease.web.Dao.ProductDao;
import com.netease.web.Dao.ShoppingCart;
import com.netease.web.meta.Buyer;
import com.netease.web.meta.Product;

public class ShoppingCartService {
	private ShoppingCart sc=null;
	private ApplicationContext ac=null;
	private SqlSessionFactory sqlSessionFactory=null;
	private SqlSession session=null;
	
	public ShoppingCartService(){
		ac=new ClassPathXmlApplicationContext("application-context.xml");
		sc=ac.getBean("shoppingCart", ShoppingCart.class);
		sqlSessionFactory=ac.getBean("sqlSessionfactory",SqlSessionFactory.class);
		session=sqlSessionFactory.openSession();
	}
	public SqlSession GetSession(){
		return session;
	}
	
	public void AddOneProductToCart(int productId,int buyerId,int BuyNum){
		//System.out.println("productId="+productId+"; buyerId="+buyerId);
		sc.addOneProduct(productId, buyerId, BuyNum);
	}
	
	public List<Product> GetProductListByBuyer(int id){
		return sc.getProductsByBuyerId(id);
	}
	
	public Integer getProductIdByBuyerId(int productId,int buyerId){
		return sc.getProductIdByBuyerId(productId, buyerId);
	}
	
	public int getBuyNum(int id){
		return sc.selectBuyNum(id);
	}
	
	public  void  updateBuyNum(int buyNum,int id){
		 sc.updateBuyNum(buyNum, id);
	}
	
	public Buyer getUserShoppingList(int id){
	    return sc.getUserShoppingList(id);	
	}
    
    public void DeleteProductsFromShoppingCart(int id){
		sc.deleteProductsForUser(id);
	}
}
