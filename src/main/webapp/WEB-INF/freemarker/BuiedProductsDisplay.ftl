<html lang="en-US">
    <head>
        <meta charset="UTF-8">
        <title>����</title>
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
               <h2>��������</h2>
            </div>
            <#if hasThing=="true" >
            <table class="m-form-ht">
                    <tr class="fmitem" style="font-size:18px;font-weight:bold;line-height:40px">
	                    <td>��ƷͼƬ</td><td> ��Ʒ��</td>  <td>����</td>  <td>����ʱ��</td>  <td>�۸�</td> 
	                </tr>
	                <#assign num= 0> 
               <#list products as product>  
	                   
	                  <tr style="line-height:100px">
	                       <td style="height:100%;width:150px;text-align:center"><span><img src=${product.picture} style="height:100px;width:100px"/></span></td><td>${product.name}</td> <td>${product.num}</td> <td>${product.date}</td><td>${product.money}Ԫ</td> 
	                  </tr>
	                  <#assign num =product.money*product.num + num  > 
	               
               </#list>
              
          </table>
          <div style="float:right;margin-right:40px">
             <span style="font-size:16px;font-weight:bold">�ܶ</span><span id="allPrice">${(num)!}Ԫ</span>
          </div>
           </#if>
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
      
    </body>
</html>