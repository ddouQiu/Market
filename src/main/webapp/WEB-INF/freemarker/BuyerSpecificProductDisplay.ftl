<html lang="en-US">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="../css/style.css">
        <script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
        <title>商品信息</title>
        <style>
            .text{
               float: right; 
               width: 79%;
            }
            .fmcontent{
              width:100%;
              height:100%;
            }
            .fmouter{
              width:1200px;
              height:500px;
              border:1px solid #d9d9d9;
              background:white;
            }
            li{
              list-style:none;
            }
        </style>
    </head>
    <body>
        <#include "/header.ftl"/>
        <div class="g-doc">
    	<div class="n-public">
        <form class="m-form m-form-ht " id="form" method="post">
            <div class="fmitem" style="height:330px">
            <span class="imgpre bpImg"><img  id="imgpre" style="width:100%;height:100%" src=${product.picture}></span>
            <div class="text">    
                <h1>${product.name}</h1>
                <p style="color:red">${product.summary}</p>
                </br>
                <span>价格：</span><span style="font-weight:bold;font-size: 18px"> ${product.price}元</span>
                <#if hasbuied=="true">
                	<p>购买数量：<span id="quantity"> ${num} </span>
                <#else>
               		<p>购买数量：<span id="minus" onclick="numDec()"> - </span><span id="quantity"> 0 </span><span id="plus" onclick="numAdd()"> + </span></p>
                </#if>
                <div class="fmitem fmitem-nolab fmitem-btn">
                <div class="fmipt" style="padding-top:30px;padding-left:20px">
                    <#if hasbuied=="true">
                        <button type="submit" class="u-btn u-btn-primary u-btn-lg theme-login"  name="submit" style="font-size: 16px;color:#A5A7A9" disabled="disabled">购买</button>
                    	<span style="color:red">已购买，当时价格：${currentprice}元</span>
                    <#else>
                        <button type="submit" class="u-btn u-btn-primary u-btn-lg theme-login"  name="submit" style="font-size: 16px" >购买</button>
                    </#if>
                </div>
            </div>
            </div>
            </div>
             <hr/>
            
            <div>
                <label class="fmlab" style="text-align:left;color:red;font-weight:bold;font-size: 18px" >商品详情：</label>
                <div class="fmouter">
                    <span class="fmcontent">${product.content}</span>
                </div>
            </div>  
            
           
        </form>
        <div class="theme-popover">
		     <div class="theme-poptit">
		          <a href="javascript:;" title="关闭" class="close">×</a>
		          <h3>确认信息</h3>
		     </div>
		     <div class="theme-popbod dform">
		           <form class="theme-signin" name="loginform" id="loginform" >
		                <ol>
		                	 <li><p style="font-size: 18px;margin-bottom:20px">确认要购买吗？</p></li>
		                     <li><button type="submit" class="u-btn u-btn-primary u-btn-lg theme-login"  id="check" style="font-size: 16px">确认</button></li>
		                     <li><button type="submit" class="u-btn u-btn-primary u-btn-lg theme-login"  name="cancel" style="font-size: 16px" >取消</button></li>
		                </ol>
		           </form>
		     </div>
	  </div>
	  <div class="theme-popover-mask"></div>
        <script>
              window.onload=function(){
	               document.getElementById("check").onclick=function(){
	                  //alert("editbtn");
	                  window.location.href="/Market/api/AddShoppingCart?produtId="+"${product.id}"+"&buyNum="+$("#quantity").html();
	               }
               }
            /*商品数量+1*/
				function numAdd(){
					var quantity = document.getElementById("quantity").innerText;
				 	var num_add = parseInt(quantity)+1;
				 	
				  	document.getElementById("quantity").innerText=num_add;
				  
			  	
				}
				/*商品数量-1*/
				function numDec(){
					var quantity = document.getElementById("quantity").innerText;
				 	var num_dec = parseInt(quantity)-1;
				 	if(num_dec>-1){
				 		document.getElementById("quantity").innerText=num_dec;
				 	}
				}
				$(document).ready(function() {
				 
					$('.theme-login').click(function(){
						$('.theme-popover-mask').fadeIn(100);
						$('.theme-popover').slideDown(200);
						return false;
				    });
					$('.theme-poptit .close').click(function(){
						$('.theme-popover-mask').fadeOut(100);
						$('.theme-popover').slideUp(200);
					});
					$("button[name='cancel']").click(function(){
						$('.theme-popover-mask').fadeOut(100);
						$('.theme-popover').slideUp(200);
						
					});
					
			   });
			   

        </script>
        </div>
        </div>
            
    </body>
</html>