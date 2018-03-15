<html lang="en-US">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="../css/style.css">
        <script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
        <title>��Ʒ��Ϣ</title>
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
                <span>�۸�</span><span style="font-weight:bold;font-size: 18px"> ${product.price}Ԫ</span>
                <#if hasbuied=="true">
                	<p>����������<span id="quantity"> ${num} </span>
                <#else>
               		<p>����������<span id="minus" onclick="numDec()"> - </span><span id="quantity"> 0 </span><span id="plus" onclick="numAdd()"> + </span></p>
                </#if>
                <div class="fmitem fmitem-nolab fmitem-btn">
                <div class="fmipt" style="padding-top:30px;padding-left:20px">
                    <#if hasbuied=="true">
                        <button type="submit" class="u-btn u-btn-primary u-btn-lg theme-login"  name="submit" style="font-size: 16px;color:#A5A7A9" disabled="disabled">����</button>
                    	<span style="color:red">�ѹ��򣬵�ʱ�۸�${currentprice}Ԫ</span>
                    <#else>
                        <button type="submit" class="u-btn u-btn-primary u-btn-lg theme-login"  name="submit" style="font-size: 16px" >����</button>
                    </#if>
                </div>
            </div>
            </div>
            </div>
             <hr/>
            
            <div>
                <label class="fmlab" style="text-align:left;color:red;font-weight:bold;font-size: 18px" >��Ʒ���飺</label>
                <div class="fmouter">
                    <span class="fmcontent">${product.content}</span>
                </div>
            </div>  
            
           
        </form>
        <div class="theme-popover">
		     <div class="theme-poptit">
		          <a href="javascript:;" title="�ر�" class="close">��</a>
		          <h3>ȷ����Ϣ</h3>
		     </div>
		     <div class="theme-popbod dform">
		           <form class="theme-signin" name="loginform" id="loginform" >
		                <ol>
		                	 <li><p style="font-size: 18px;margin-bottom:20px">ȷ��Ҫ������</p></li>
		                     <li><button type="submit" class="u-btn u-btn-primary u-btn-lg theme-login"  id="check" style="font-size: 16px">ȷ��</button></li>
		                     <li><button type="submit" class="u-btn u-btn-primary u-btn-lg theme-login"  name="cancel" style="font-size: 16px" >ȡ��</button></li>
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
            /*��Ʒ����+1*/
				function numAdd(){
					var quantity = document.getElementById("quantity").innerText;
				 	var num_add = parseInt(quantity)+1;
				 	
				  	document.getElementById("quantity").innerText=num_add;
				  
			  	
				}
				/*��Ʒ����-1*/
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