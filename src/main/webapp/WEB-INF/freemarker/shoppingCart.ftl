<html lang="en-US">
    <head>
        <meta charset="UTF-8">
        <title>购物车</title>
        <style>
          td{
            border: 1px solid #083e0e;
          }
          .shop{
             width:100px;
            
          }
        </style>
    </head>
    <body>
    	  <#include "/header.ftl"/>
    	 
    	  <div class="g-doc">
    	    <div class="m-tab f-cb">
               <h2>购物车</h2>
            </div>
            <#if hasThing=="true" >
            <table class="m-form-ht">
                    <tr class="fmitem" style="font-size:18px;font-weight:bold;line-height:40px">
	                     <td> 商品名</td>   <td>价格</td>   <td>数量</td>
	                </tr>
               <#list products as product>  
	                   
	                  <tr>
	                     <td>${product.name}</td> <td>${product.price}元</td> <td>${Buy[product_index]}</td>
	                  </tr>
	               
               </#list>
              
          </table>
           <div style="bottom:20px;margin-top:30px;text-align:right">
                   <button type="submit" class="u-btn u-btn-primary u-btn-lg shop"  id="buy" style="font-size: 14px">购买</button>
		           <button type="submit" class="u-btn u-btn-primary u-btn-lg shop"  id="exit" style="font-size: 14px" >退出</button>
           </div>
           <script>
            window.onload=function(){
               document.getElementById("exit").onclick=function(){
                  window.location.href=document.referrer;
               }
               
               document.getElementById("buy").onclick=function(){
                  //alert("editbtn");
                  window.location.href="/Market/api/BuyProducts";
               }
            }
        </script>
      </div>
      </#if>
      
    </body>
</html>