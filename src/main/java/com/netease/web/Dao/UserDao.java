package com.netease.web.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.netease.web.meta.Buyer;
import com.netease.web.meta.Product;
import com.netease.web.meta.Seller;

public interface UserDao {
	
	@Select("select * from buyer where username=#{username}")
    public Buyer getBuyer(String username);
	
	@Select("select * from seller where username=#{username}")
    public Seller getSeller(String username);
	
	@Select("select id from seller where username=#{username}")
	public int getIdBySellerName(String username);
	
	@Select("select id from buyer where username=#{username}")
	public int getIdByBuyerName(String username);
	
}
