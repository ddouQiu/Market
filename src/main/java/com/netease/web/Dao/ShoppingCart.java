package com.netease.web.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.netease.web.meta.Buyer;
import com.netease.web.meta.Product;

public interface ShoppingCart {
	
	@Insert("INSERT INTO shoppingcart(productId,buyerId,BuyNum) VALUES(#{productId},#{buyerId},#{BuyNum}) ")
	public void addOneProduct(@Param("productId") int productId,@Param("buyerId") int buyerId,@Param("BuyNum") int BuyNum);
	
	@Results({
		@Result(property="id",column="id"),
		@Result(property="name",column="name"),
		@Result(property="price",column="price"),
		@Result(property="summary",column="abstract"),
		@Result(property="picture",column="picture"),
		@Result(property="content",column="content")
	})
	@Select("select * from product where id in (select productId from shoppingcart where id=#{id})")
	public List<Product> getProductsByBuyerId(int id);
	
	
	@Select("select id from shoppingcart where productId=#{productId} and buyerId=#{buyerId}")
	public Integer getProductIdByBuyerId(@Param("productId") int productId,@Param("buyerId") int buyerId);
	
	@Update("update shoppingcart SET BuyNum=#{buyNum} WHERE id = #{id}  ")
	public void updateBuyNum(@Param("buyNum") int buyNum,@Param("id")int id);
	
	@Select("select BuyNum from shoppingcart where id = #{id}")
	public int selectBuyNum(int id);
	
	
	public Buyer getUserShoppingList(int id);
	
	@Delete("delete from shoppingcart where buyerId = #{id}")
	public void deleteProductsForUser(int id);

}
