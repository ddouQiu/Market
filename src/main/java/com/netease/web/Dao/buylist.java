package com.netease.web.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import com.netease.web.meta.Buyer;
import com.netease.web.meta.Product;


public interface buylist {
	
	public void batchInsert(@Param("listbuyerId") int listbuyerId,@Param("list") List<Product> products);
	
	public Buyer SelectAll(@Param("listbuyerId") int listbuyerId);
	
	@Select("select money from buylist where listbuyerId=#{listbuyerId} and listproductId=#{listproductId}")
	public float getCurrentPrice(@Param("listbuyerId") int listbuyerId,@Param("listproductId") int listproductId);
	
	@Select("select num from buylist where listbuyerId=#{listbuyerId} and listproductId=#{listproductId}")
	public int getbuyNumPrice(@Param("listbuyerId") int listbuyerId,@Param("listproductId") int listproductId);

}
