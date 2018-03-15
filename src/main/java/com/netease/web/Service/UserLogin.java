package com.netease.web.Service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netease.web.Dao.UserDao;
import com.netease.web.meta.Buyer;
import com.netease.web.meta.Seller;

public class UserLogin {
	public static String HasUser(String username,String password){
		ApplicationContext ac=new ClassPathXmlApplicationContext("application-context.xml");
		UserDao userDao=ac.getBean("userDao", UserDao.class);
		Buyer buyer=userDao.getBuyer(username);
		String str=null;
		if(buyer!=null && password.equals(buyer.getPassword())){
			str="buyer";
		}else{
		    Seller seller=userDao.getSeller(username);
		    if(seller!=null&&password.equals(seller.getPassword())){
		    	str="seller";
		    }
	     }
		return str;
	}
	
}
