package com.netease.web.controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.netease.web.Dao.ProductDao;
import com.netease.web.Dao.ShoppingCart;
import com.netease.web.Dao.buylist;
import com.netease.web.Service.BuyListService;
import com.netease.web.Service.ProductService;
import com.netease.web.Service.ShoppingCartService;
import com.netease.web.Service.UserLogin;
import com.netease.web.Service.UserService;
import com.netease.web.meta.Buyer;
import com.netease.web.meta.Product;
import com.netease.web.meta.Seller;
import com.netease.web.util.Tools;

@Controller
public class MarketController {
	
	@RequestMapping(value="/login")
	public ModelAndView Login(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("passwd") String passwd,HttpServletRequest request,HttpServletResponse response,ModelMap map){
		String UserType;
		ModelAndView mav=null;
		//考虑多次登陆情况,换身份会有迟钝
		if((UserType=UserLogin.HasUser(username, passwd))!=null){
			Tools.CreateCookie("userName", username, response);
			Tools.CreateCookie("userPassword", passwd, response);
			Tools.CreateCookie("userType", UserType, response);
			//System.out.println("login");
		    mav=new ModelAndView("redirect:/api/showAllProduct");
		}else{
			//登录失败
			map.addAttribute("message", "用户名或密码错误，请重新登录");
			mav=new ModelAndView("UserLogin",map);
		}
		return mav;
	}
	
	@RequestMapping(value="/publish")
	public ModelAndView publish(HttpServletRequest request,ModelMap map){
		String userType=null;
		String userName=null;
		Cookie[] cookies=request.getCookies();
		for(Cookie cookie:cookies){
			if(cookie.getName().equals("userType")){
				userType=cookie.getValue();
			}
			if(cookie.getName().equals("userName")){
				userName=cookie.getValue();
			}
		}
		map.addAttribute("username", userName);
		map.addAttribute("userType", userType);
		ModelAndView mav=new ModelAndView("publish",map);
		return mav;
	}
	
	@RequestMapping(value="/publicSubmit")
	public ModelAndView publicSubmit(HttpServletRequest request,HttpServletResponse response){
		//System.out.println("enter publicSubmit");
		Product product=new Product();
		product.setName(request.getParameter("title"));
		product.setPrice(Float.valueOf(request.getParameter("price")));
		product.setContent(request.getParameter("detail"));
		product.setSummary(request.getParameter("summary"));
		
		//Tools.CreateCookie("title", request.getParameter("title"), response);
		//System.out.println(request.getParameter("title")+"1111111");
		
		//获取图片信息
		//缺：图片框要随着选择而决定是否显示
		String picture=null;
		//System.out.println(request.getParameter("image")+"hello");
		if(request.getParameter("image").equals("")){
			//System.out.println("enter multi");
			MultipartFile file =  ((MultipartHttpServletRequest) request).getFile("file");
			picture=Tools.UpLoad(file, request);
		}else{
			picture=request.getParameter("image");
		}
		product.setPicture(picture);
		
		//获取seller身份信息
		//System.out.println("type");
		Cookie[] cookies=request.getCookies();
		String userType=null;
		String userName=null;
		for(Cookie cookie:cookies){
			if(cookie.getName().equals("userType")){
				userType=cookie.getValue();
			}
			if(cookie.getName().equals("userName")){
				userName=cookie.getValue();
			}
		}
		Seller seller=null;
		UserService us=new UserService();
		if(userType.equals("seller")){
			seller=us.GetSeller(userName);
		}
		product.setSeller(seller);
		ProductService ps=new ProductService();
		ps.AddProduct(product);
		//请求转发
		ModelAndView mav=new ModelAndView("redirect:/api/checkProduct?productName="+product.getName());
		return mav;
	}
	
	@RequestMapping(value="/upload")
	@ResponseBody
	public String upLoad(HttpServletRequest request){
		String picture=null;
		if(request instanceof MultipartHttpServletRequest){
			MultipartFile file =  ((MultipartHttpServletRequest) request).getFile("file");
		    picture=Tools.UpLoad(file, request);
		   
		}
		 return picture;
	 } 
	
	@RequestMapping(value="/showAllProduct")
	public ModelAndView ShowProduct(HttpServletRequest request,ModelMap map){
		ProductService pd=new ProductService();
		List<Product> list=pd.GetAllProduct();
        
		//获取当前用户信息
		Cookie[] cookies=request.getCookies();
		String userType=null;
		String userName=null;
		for(Cookie cookie:cookies){
			if(cookie.getName().equals("userType")){
				userType=cookie.getValue();
			}
			if(cookie.getName().equals("userName")){
				userName=cookie.getValue();
			}
		}
		ArrayList<String> judgeHasBuied=new ArrayList<String>();
		if(userType!=null &&userType.equals("buyer")){
			int userId=0;
			UserService us=new UserService();
			userId=us.GetIdByBuyerName(userName);
			for(Product p:list){
				if(pd.hasBuied(p.getId(), userId)){
					judgeHasBuied.add("true");
				}else{
					judgeHasBuied.add("false");
				}
			}
		}
		//System.out.println(userName+"11111");
		map.addAttribute("username", userName);
		map.addAttribute("userType", userType);
		map.addAttribute("ProductList", list);
		map.addAttribute("judgeHasBuied", judgeHasBuied);
		ModelAndView mav=new ModelAndView("show",map);
		return mav;
	}

	@RequestMapping(value="DeleteSpecifiedProduct")
	public void DeleteSpecifiedProduct(HttpServletRequest request,HttpServletResponse response){
		String productName=request.getParameter("productName");
		//System.out.println("enter"+productName);
		ProductService ps=new ProductService();
		ps.DeleteSpecifiedProductByName(productName);
		RequestDispatcher rd=request.getRequestDispatcher("/api/showAllProduct");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/checkProduct")
	public ModelAndView CheckProduct(HttpServletRequest request,ModelMap map){
		ModelAndView mav;
		//System.out.println("hello");
		String productName=request.getParameter("productName");
		ProductService ps=new ProductService();
		Product product=ps.CheckSpecifiedProduct(productName);
		String userType=null;
		String userName=null;
		Cookie[] cookies=request.getCookies();
		for(Cookie cookie:cookies){
			if(cookie.getName().equals("userType")){
				userType=cookie.getValue();
			}
			if(cookie.getName().equals("userName")){
				userName=cookie.getValue();
			}
		}
		map.addAttribute("username", userName);
		map.addAttribute("userType", userType);
		map.addAttribute("product", product);
		///System.out.println(product.getContent());
		mav=new ModelAndView("sellerSpecificProductDisplay",map);
		return mav;
	}
	
	@RequestMapping(value="/EditProduct")
	public ModelAndView EditProduct(HttpServletRequest request,ModelMap map){
		ModelAndView mav;
		//System.out.println("hello");
		String productName=request.getParameter("productName");
		ProductService ps=new ProductService();
		Product product=ps.CheckSpecifiedProduct(productName);
		String userType=null;
		String userName=null;
		Cookie[] cookies=request.getCookies();
		for(Cookie cookie:cookies){
			if(cookie.getName().equals("userType")){
				userType=cookie.getValue();
			}
			if(cookie.getName().equals("userName")){
				userName=cookie.getValue();
			}
		}
		map.addAttribute("username", userName);
		map.addAttribute("userType", userType);
		map.addAttribute("product", product);
		///System.out.println(product.getContent());
		mav=new ModelAndView("ProductEdit",map);
		return mav;
	}
	
	@RequestMapping(value="SubmitEdit")
	public ModelAndView SubmitEdit(HttpServletRequest request,HttpServletResponse response){
		//System.out.println("enter publicSubmit");
				Product product=new Product();
				product.setName(request.getParameter("title"));
				product.setPrice(Float.valueOf(request.getParameter("price")));
				product.setContent(request.getParameter("detail"));
				product.setSummary(request.getParameter("summary"));
				product.setId(Integer.valueOf(request.getParameter("id")));
				
				//获取图片信息
				//缺：图片框要随着选择而决定是否显示
				String picture=null;
				//System.out.println(request.getParameter("image")+"hello");
				if(request.getParameter("image").equals("")){
					//System.out.println("enter multi");
					MultipartFile file =  ((MultipartHttpServletRequest) request).getFile("file");
					picture=Tools.UpLoad(file, request);
				}else{
					picture=request.getParameter("image");
				}
				product.setPicture(picture);
				
				//获取seller身份信息
				//System.out.println("type");
				Cookie[] cookies=request.getCookies();
				String userType=null;
				String userName=null;
				for(Cookie cookie:cookies){
					if(cookie.getName().equals("userType")){
						userType=cookie.getValue();
					}
					if(cookie.getName().equals("userName")){
						userName=cookie.getValue();
					}
				}
				Seller seller=null;
				UserService us=new UserService();
				if(userType.equals("seller")){
					seller=us.GetSeller(userName);
				}
				product.setSeller(seller);
				ProductService ps=new ProductService();
				ps.updateSpecificProduct(product);
				//请求转发
				ModelAndView mav=new ModelAndView("redirect:/api/checkProduct?productName="+product.getName());
				return mav;
		
	}
	
	@RequestMapping(value="checkProductByBuyer")
	public ModelAndView checkProductByBuyer(HttpServletRequest request,ModelMap map){
		String productName=request.getParameter("productName");
		//System.out.println("buyercheck");
		ProductService ps=new ProductService();
		Product product=ps.CheckSpecifiedProduct(productName);
		map.addAttribute("product", product);
		//获取当前用户信息
		int userId=Tools.getCurrentUserId(request,map);
		float price=-1;
		int num=0;
		String hasBuied="false";
		BuyListService bls=new BuyListService();
		if(ps.hasBuied(product.getId(), userId)){
			price=bls.getCurrentPrice(userId, product.getId());
			num=bls.getbuyNumPrice(userId, product.getId());
			hasBuied="true";
		}
		map.addAttribute("currentprice", price);
		map.addAttribute("hasbuied", hasBuied);
		map.addAttribute("num", num);
		ModelAndView mav=new ModelAndView("BuyerSpecificProductDisplay",map);
		return mav;
	}
	
	@RequestMapping(value="/AddShoppingCart")
	public ModelAndView AddShoppingCart(HttpServletRequest request,ModelMap map){
		int productId=Integer.valueOf(request.getParameter("produtId"));
		//System.out.println(request.getParameter("buyNum")+"1111111");
		int buyNum=Integer.valueOf(request.getParameter("buyNum"));
		
		Cookie[] cookies=request.getCookies();
		String userType=null;
		String userName=null;
		for(Cookie cookie:cookies){
			if(cookie.getName().equals("userType")){
				userType=cookie.getValue();
			}
			if(cookie.getName().equals("userName")){
				userName=cookie.getValue();
			}
		}
		int userId=0;
		UserService us=new UserService();
		userId=us.GetIdByBuyerName(userName);
		map.addAttribute("username", userName);
		map.addAttribute("userType", userType);
		//System.out.println("buyerId="+userId);
		ShoppingCartService scc=new ShoppingCartService();
		if(scc.getProductIdByBuyerId(productId, userId)!=null){
		   int id=scc.getProductIdByBuyerId(productId, userId);
		   int Num=scc.getBuyNum(id);
		   scc.updateBuyNum(buyNum+Num, id);
		}else{
			scc.AddOneProductToCart(productId,userId, buyNum);
		}
		
		
		SqlSession session=scc.GetSession();
		ShoppingCart shoppingCart=session.getMapper(ShoppingCart.class);
		Buyer buyer=shoppingCart.getUserShoppingList(userId);
		
//		Buyer buyer=scc.getUserShoppingList(userId);
		List<Integer> BuyNum=new ArrayList<Integer>();
		for(Product p : buyer.getProducts()){
			//System.out.println(p.getBuyNum());
			//buy=p.getBuyNum();
			BuyNum.add(p.getBuyNum());
		}
		String hasThing="true";
		map.addAttribute("hasThing",hasThing);
		map.addAttribute("products", buyer.getProducts());//前端获取不到BuyNum
		map.addAttribute("Buy",BuyNum);
		ModelAndView mav=new ModelAndView("shoppingCart",map);
		
		return mav;
	}
	
	@RequestMapping(value="/shoppingCart")
	public ModelAndView shoppingCart(HttpServletRequest request,ModelMap map){
		ShoppingCartService scc=new ShoppingCartService();
		SqlSession session=scc.GetSession();
		ShoppingCart shoppingCart=session.getMapper(ShoppingCart.class);
		
		String userType=null;
		String userName=null;
		Cookie[] cookies=request.getCookies();
		for(Cookie cookie:cookies){
			if(cookie.getName().equals("userType")){
				userType=cookie.getValue();
			}
			if(cookie.getName().equals("userName")){
				userName=cookie.getValue();
			}
		}
		int userId=0;
		UserService us=new UserService();
		userId=us.GetIdByBuyerName(userName);
		map.addAttribute("username", userName);
		map.addAttribute("userType", userType);
		Buyer buyer=shoppingCart.getUserShoppingList(userId);
		List<Integer> BuyNum=new ArrayList<Integer>();
		String hasThing="true";
		//System.out.println("hasThing:"+hasThing);
		if(buyer!=null && buyer.getProducts()!=null){
			for(Product p : buyer.getProducts()){
				//System.out.println(p.getBuyNum());
				//buy=p.getBuyNum();
				BuyNum.add(p.getBuyNum());
			}
			map.addAttribute("products", buyer.getProducts());//前端获取不到BuyNum
			map.addAttribute("Buy",BuyNum);
		}else{
			hasThing="false";
		}
		map.addAttribute("hasThing",hasThing);
		ModelAndView mav=new ModelAndView("shoppingCart",map);
		//System.out.println("hasThing:"+hasThing);
		return mav;
	}
	
	@RequestMapping(value="/BuyProducts")
	public ModelAndView BuyProducts(HttpServletRequest request,ModelMap map){
		ShoppingCartService scc=new ShoppingCartService();
		SqlSession session=scc.GetSession();
		ShoppingCart shoppingCart=session.getMapper(ShoppingCart.class);
		
		int userId=Tools.getCurrentUserId(request,map);
		Buyer buyer=shoppingCart.getUserShoppingList(userId);
		
		BuyListService bls=new BuyListService();
		SqlSession session1=bls.GetSession();
		buylist list=session1.getMapper(buylist.class);
		list.batchInsert(userId, buyer.getProducts());
		
		ProductService ps=new ProductService();
		SqlSession session11=ps.GetSession();
		ProductDao productDao=session11.getMapper(ProductDao.class);
		productDao.BatchUpdateSellNum(buyer.getProducts());
		
		//System.out.println("update sellnum111111");
		
		scc.DeleteProductsFromShoppingCart(userId);
		ModelAndView mav=new ModelAndView("redirect:/api/AllBuiedProductsDisplay");
		return mav;
	}
	
	@RequestMapping(value="/AllBuiedProductsDisplay")
	public ModelAndView AllBuiedProductsDisplay(HttpServletRequest request,ModelMap map){
		int userId=Tools.getCurrentUserId(request,map);
		BuyListService bls=new BuyListService();
		SqlSession session1=bls.GetSession();
		buylist list=session1.getMapper(buylist.class);
		Buyer buyer=list.SelectAll(userId);
		
		String hasThing="true";
		//System.out.println("hasThing:"+hasThing);
		if(buyer!=null && buyer.getProducts()!=null){
			map.addAttribute("products", buyer.getProducts());
		}else{
			hasThing="false";
		}
		map.addAttribute("hasThing",hasThing);
		ModelAndView mav=new ModelAndView("BuiedProductsDisplay",map);
		return mav;
	}
	
	@RequestMapping(value="checkNotBuiedProducts")
	public ModelAndView checkNotBuiedProducts(HttpServletRequest request,ModelMap map){
		ProductService ps=new ProductService();
		int userId=Tools.getCurrentUserId(request,map);
		List<Product> products=ps.getUnBuiedProducts(userId);
		map.addAttribute("products", products);
		ModelAndView mav=new ModelAndView("NotBuiedProducts",map);
		return mav;
		
	}
	
}
