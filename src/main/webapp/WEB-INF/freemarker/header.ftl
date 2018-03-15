<html lang="en-US">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="../css/style.css">
    </head>
    
    <body>
    <div class="n-head">
    
    <#if ((username)!"null")=="buyer" || ((username)!"null")=="seller">
    
     <div class="g-doc f-cb">
	        <div class="user">
	            <p>欢迎 ${username}<#if userType == "seller">卖家!<#else>买家！</#if>  <a href="/Market/UserLogin.jsp">[退出]</a> </p>
	        </div>
	        <ul class="nav">
	            <li><a href="/Market/api/showAllProduct">首页</a></li> <#if userType == "buyer"> <li><a href="/Market/api/AllBuiedProductsDisplay">财务</a></li>  <li><a href="/Market/api/shoppingCart">购物车</a></li></#if>
	            <#if userType == "seller">
	            <li><a href="/Market/api/publish">发布</a></li>
	            </#if>
	        </ul>
	</div>
   
    <#else>
          <div class="g-doc f-cb">
			       <div class="user">
			                    请<a href="/Market/UserLogin.jsp">[登录]</a>
			       </div>
			       <ul class="nav">
			         <li><a href="/Market/api/showAllProduct">首页</a></li>
			       </ul>
          </div>
	        
   </#if>
    </div>
    </body>
</html>