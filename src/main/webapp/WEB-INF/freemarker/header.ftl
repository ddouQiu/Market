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
	            <p>��ӭ ${username}<#if userType == "seller">����!<#else>��ң�</#if>  <a href="/Market/UserLogin.jsp">[�˳�]</a> </p>
	        </div>
	        <ul class="nav">
	            <li><a href="/Market/api/showAllProduct">��ҳ</a></li> <#if userType == "buyer"> <li><a href="/Market/api/AllBuiedProductsDisplay">����</a></li>  <li><a href="/Market/api/shoppingCart">���ﳵ</a></li></#if>
	            <#if userType == "seller">
	            <li><a href="/Market/api/publish">����</a></li>
	            </#if>
	        </ul>
	</div>
   
    <#else>
          <div class="g-doc f-cb">
			       <div class="user">
			                    ��<a href="/Market/UserLogin.jsp">[��¼]</a>
			       </div>
			       <ul class="nav">
			         <li><a href="/Market/api/showAllProduct">��ҳ</a></li>
			       </ul>
          </div>
	        
   </#if>
    </div>
    </body>
</html>