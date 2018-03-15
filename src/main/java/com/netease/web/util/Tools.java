package com.netease.web.util;

import java.io.File;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.netease.web.Service.UserService;

public class Tools {
	public static void CreateCookie(String name,String value,HttpServletResponse response){
		Cookie n=new Cookie(name,value);
		response.addCookie(n);
	}
	
	public static String UpLoad(MultipartFile file,HttpServletRequest request){
		String path="E:/upload";
		//System.out.println(path);
		String fileName = file.getOriginalFilename(); 
		//System.out.println(fileName);
		File targetFile = new File(path, fileName);  
		if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	    }  
	   
	    //±£´æ  
	    try {  
	           file.transferTo(targetFile);  
	    } catch(Exception e) {  
	            e.printStackTrace(); 
	    }  
	    //return "E:/upload/"+fileName;
	    return "/upload/"+fileName;
	}
	
	public static int getCurrentUserId(HttpServletRequest request,ModelMap map){
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
		return userId;
	}

}
