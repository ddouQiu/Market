package com.netease.web.Service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netease.web.Dao.buylist;
import com.netease.web.meta.Product;

public class BuyListService {
	
	private buylist buylistdao=null;
	private ApplicationContext ac=null;
	private SqlSessionFactory sqlSessionFactory=null;
	private SqlSession session=null;
	
	public BuyListService(){
		ac=new ClassPathXmlApplicationContext("application-context.xml");
		buylistdao=ac.getBean("buylist", buylist.class);
		sqlSessionFactory=ac.getBean("sqlSessionfactory",SqlSessionFactory.class);
		session=sqlSessionFactory.openSession();
	}
	
	public void BatchAddProductsToList(int listbuyerId,List<Product> products){
		buylistdao.batchInsert(listbuyerId, products);
	}
	
	public SqlSession GetSession(){
		return session;
	}
	
	public float getCurrentPrice(int listbuyerId, int listproductId){
		return buylistdao.getCurrentPrice(listbuyerId, listproductId);
	}
	
	public int getbuyNumPrice( int listbuyerId,int listproductId){
		return buylistdao.getbuyNumPrice(listbuyerId, listproductId);
	}
	
}
