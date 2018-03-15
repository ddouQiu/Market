<html lang="en-US">

    <head>
        <meta charset="UTF-8">
        <title>��ҳ</title>
        <style type="text/css">
           .shu{
               margin:0px 40px;  
               list-style-type:none;
            }
           img{
               width: 200px;
               height: 300px;
           }
           
           .heng{
               float: left;
               margin:0px 40px;  
               list-style-type:none;
           }
           
           .container{
              width: 1200px;
              margin: 0 auto;
           }
           #span1{
                position: absolute;/*���Զ�λ*/
                width: 200px;
                text-align: left;
                font-size: 14px;
                color: black;
            }  
           #span2{
                position: absolute;/*���Զ�λ*/
                width: 200px;
                text-align: right;
                font-size: 14px;
                color: black;
           }  
           .la{
                text-align: center;
                margin:10px 0px;
            }
            
        </style>
    </head>
    <body>
       <#include "/header.ftl"/>
       <div>
           <div class="container" id="wrap">
           <div class="m-tab f-cb">
               <span style="font-size:14px;font-weight:normal" id="allProducts">ȫ����Ʒ<//span> |
               <span><label id="notbuied" style="font-size:16px;font-weight:bold">δ������Ʒ</label></span>
            </div>
               <#list products as product>  
	                <ul>
	                   <#if (product_index+1) % 4 != 0>
				           <li class="heng">
					           <div>
					                <span id="span1">${product.name}</span>
					                <#if username == "seller" >
					           			<a href=${"/Market/api/checkProduct?productName=${product.name}"}><img src=${product.picture} id="pic"></a>
					           		</#if>
					           		<#if username == "buyer" >
					           		   
					           		    <a href=${"/Market/api/checkProductByBuyer?productName=${product.name}"}><img src=${product.picture} id="pic"></a>
					           		</#if>
					           </div>
					           <div class="la">
					           		<label>${product.price} Ԫ</label>
					           		<#if username == "seller">
					           		   <label> �ѳ�����Ŀ��${product.sellNum}</label>
					           		</#if>
					           </div>
					           <#if username == "seller">
						           <div>
						                <button class="u-btn u-btn-primary u-btn-lg showBtn" id=${product.name}  >ɾ��</button>
						           </div>
					           </#if>
				           </li>
			           </#if>
			           
			           <#if (product_index+1) % 4 == 0>
				            <li class="shu">
					            <div class="ImageContainer">
					                <span id="span1">${product.name}</span>
					                <#if username == "seller" >
					            		<a href=${"/Market/api/checkProduct?productName=${product.name}"}><img src=${product.picture}></a>
					            	</#if>
					            	<#if username == "buyer" >
					           			<a href=${"/Market/api/checkProductByBuyer?productName=${product.name}"}><img src=${product.picture} id="pic"></a>
					           		</#if>
					            </div>
					            <div class="la">
					          		<label>${product.price} Ԫ</label>
					          		<#if username == "seller" >
					           		   <label> �ѳ�����Ŀ��${product.sellNum}</label>
					           		</#if>
					            </div>
					            <div>
					              <#if username == "seller">
					                <button class="u-btn u-btn-primary u-btn-lg showBtn" id=${product.name} style="width:200px" >ɾ��</button>
					              </#if>
					            </div>
				            </li>
			           </#if>
	               </ul>
               </#list>
          </div>
      </div>
      
      <script>
       window.onload=function(){
          var btn=wrap.getElementsByTagName('button');
          for(var i in btn){
                btn[i].onclick=function(){
                    //alert(this.id);
                    window.location.href="/Market/api/DeleteSpecifiedProduct?productName="+this.id;
                }
          } 
         document.getElementById('notbuied').onclick=function(){
               window.location.href="/Market/api/checkNotBuiedProducts";
          }
          
         document.getElementById('allProducts').onclick=function(){
               window.location.href="/Market/api/showAllProduct";
          }
      }
       
      </script>
    </body>

</html>