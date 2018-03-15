package com.netease.web.Dao;



import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.netease.web.meta.Product;

public interface ProductDao {
	
	@Insert("INSERT INTO product(name,price,abstract,content,picture,sellnum,sellerId) VALUES(#{name},#{price},#{summary},#{content},#{picture},#{sellNum},#{seller.id})")  
    public void insert(Product product);  
	
	@Select("select * from product")
	public List<Product> selectAll();
	
	@Delete("DELETE FROM product WHERE name = #{productName}")
	public void deletSpecifiedProduct(String productName);
	
	@Results({
		@Result(property="id",column="id"),
		@Result(property="name",column="name"),
		@Result(property="price",column="price"),
		@Result(property="summary",column="abstract"),
		@Result(property="picture",column="picture"),
		@Result(property="content",column="content")
	})
	@Select("select * from product where name=#{productName}")
	public Product CheckSpecifiedProduct(String productName);
	
	@Update("update product SET name=#{name},price=#{price},abstract=#{summary},picture=#{picture},content=#{content} WHERE id = #{id} ")
	public void UpdateSpecificProduct(Product product);
	
	@Update("update product SET sellnum=#{sellnum} WHERE id = #{productId}")
	public void updateSellNum(@Param("sellnum") int sellnum,@Param("productId") int productId);
	
	public void BatchUpdateSellNum(@Param("list") List<Product> products);
	
	
	@Select("select count(*) from product inner join buylist on product.id=buylist.listproductId inner join buyer on buyer.id=buylist.listbuyerId where product.id=#{productId} and buyer.id=#{buyerId}")
	public int hasBuied(@Param("productId") int productId,@Param("buyerId") int buyerId);
	
	@Results({
		@Result(property="summary",column="abstract"),
	})
	@Select("select id,name,picture,price,abstract,content from product p where id not in (select listproductId from buylist where listbuyerId=#{buyerId})")
	public List<Product> getNotBuiedProducts(@Param("buyerId") int buyerId);
	
	

}
