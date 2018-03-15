package com.netease.web.Service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netease.web.Dao.UserDao;
import com.netease.web.meta.Seller;


public class UserService {
	private UserDao userDao=null;
	private ApplicationContext ac=null;
	
	public UserService(){
		ac=new ClassPathXmlApplicationContext("application-context.xml");
		userDao=ac.getBean("userDao", UserDao.class);
	}
	
	public int GetIdBySellerName(String username){
		return userDao.getIdBySellerName(username);
	}
	
	public Seller GetSeller(String username){
		return userDao.getSeller(username);
	}
	
	public int GetIdByBuyerName(String username){
		return userDao.getIdByBuyerName(username);
	}
	
}
