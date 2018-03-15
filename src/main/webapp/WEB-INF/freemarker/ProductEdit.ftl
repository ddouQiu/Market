<html lang="en-US">

<html>
  <head>
     <title>商品编辑页</title>
     <link rel="stylesheet" type="text/css" href="../css/style.css">
     <script type="text/javascript" src="../js/jquery-3.3.1.js"></script> 
  </head>
  <body>
  <#include "/header.ftl"/>
  <div class="g-doc">
    <div class="m-tab f-cb">
        <h2>商品编辑</h2>
    </div>
    <div class="n-public">
        <form class="m-form m-form-ht" id="form" method="post">
            <div class="fmitem">
                <label class="fmlab" id="ti">标题：</label>
                <div class="fmipt">
                    <input class="u-ipt ipt" name="title" autofocus="" placeholder="2-80字符" value=${product.name}>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">摘要：</label>
                <div class="fmipt">
                    <input class="u-ipt ipt" name="summary" placeholder="2-140字符" value=${product.summary}> 
                </div>
            </div>
            <div class="fmitem">
            <label class="fmlab">图片：</label>
            	<div class="fmipt" id="uploadType">
	                <input name="pic" type="radio" value="url" checked="" onchange="URLOn(this)"> 图片地址
					<input name="pic" type="radio" value="file" onchange="FileOn(this)"> 本地上传
				</div>	
            </div>
             <script type="text/javascript">
            
            function URLOn(x){
            	if(x.checked){
	            	document.getElementById("urlUpload").style.display="block";
	                document.getElementById("fileUpload").style.display="none";
	                document.getElementById("form").enctype="application/x-www-form-urlencoded";
            	}
            }
            function FileOn(x){
            	if(x.checked){
	            	document.getElementById("urlUpload").style.display="none";
	                document.getElementById("fileUpload").style.display="block";
	                document.getElementById("form").enctype="multipart/form-data";
	                document.getElementById("image").value="";
            	}
            }
            
            </script>
            <div class="fmitem">
                <label class="fmlab"></label>
                <div class="fmipt" id="urlUpload">
                    <input class="u-ipt ipt" name="image" placeholder="图片地址" id="image" value=${product.picture}>
                </div>
                <div class="fmipt" id="fileUpload" style="display:none">
                    <input class="u-ipt ipt" name="file" type="file" id="fileUp">
                    <button class="u-btn u-btn-primary" id="upload">上传</button>
                </div>
            </div>
            
            <div class="fmitem">
                <label class="fmlab">正文：</label>
                <div class="fmipt">
                    <textarea class="u-ipt" name="detail" rows="10" placeholder="2-1000个字符" >${product.content}</textarea> 
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">价格：</label>
                <div class="fmipt">
                    <input class="u-ipt price" name="price" value=${product.price}>元
                </div>
            </div>
            <div class="fmitem fmitem-nolab fmitem-btn">
                <div class="fmipt">
                    <button  class="u-btn u-btn-primary u-btn-lg"  id="EditBtn" name="EditBtn">提交</button>
                    <button  class="u-btn u-btn-primary u-btn-lg"  name="Cancel">取消</button>
                </div>
            </div>
        </form>
        
        <span class="imgpre"><img  id="imgpre" src=${product.picture} ></span>
        <script type="text/javascript">
        
                window.onload=function(){//当页面所有内容都加载完了才执行
            	$("#upload").click(
            		function(){
            			var fileObj = document.getElementById("fileUp").files[0]; // js 获取文件对象
                        if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
                            alert("请选择图片");
                            return;
                        }
                        var formFile = new FormData();
                        formFile.append("file", fileObj); //加入文件对象  
                        
            	    // alert("hello1");
                	     var data=formFile;
                		 $.ajax({
                				type:"POST",
                				url: "/Market/api/upload",
                				data:data,
                				datatype:"text",
                				processData: false,
                				contentType: false,
                				cache: false,
                				success:function(data){
                					$("#imgpre").attr("src", data);
                					//alert("hello"+data);
                				}
                		   }); 
                		 return false;
            		   
                    });
            };  
                
        </script>
      </div>
</div>
       <script>
	         
              $(document).ready(function(){
            	   var ok1,ok2,ok3,ok4; 
            	   ok3=true;
            	   ok1=true;
            	   ok2=true;
            	   ok4=true;
	               $("input[name='title']").blur(function(){
	            	   var title= $("input[name='title']").val();
	            	  // alert("hello");
	            	   if(title.length<2 || title.length>140){
	            		      $(this).css( "border", "1px solid #090");
		                	  ok1=false;
		                }else{
		                	  $(this).css( "border", "1px solid #d9d9d9");
		                	  ok1=true;
		                }
	               });
	               
	               $("input[name='summary']").blur(function(){
	            	   var summary= $("input[name='summary']").val();
	            	   //alert("hello");
	            	   if(summary.length<2 || summary.length>80){
		                	  $(this).css( "border", "1px solid #090");
		                	  ok2=false;
		                }else{
		                	  $(this).css( "border", "1px solid #d9d9d9");
		                	  ok2=true;
		                }
	               });
	               
              
	               $("input[name='image']").blur(function(){
	            	   var imageInput= $(this).val();
	            	   
	            	   //alert("hello");
	            	   if($("div[name='urlUpload']").css("display")!="none" && imageInput.length<1 ){
		                	  $(this).css( "border", "1px solid #090");
		                	  ok3=false;
		                }else{
		                	  $(this).css( "border", "1px solid #d9d9d9");
		                	  ok3=true;
		                }
	            	   
	            	  
	               }); 
	               

	               $("textarea[name='detail']").blur(function(){
	            	   var summary= $(this).val();
	            	   //alert("hello");
	            	   if(summary.length<2 || summary.length>1000){
		                	  $(this).css( "border", "1px solid #090");
		                	  ok4=false;
		                }else{
		                	  $(this).css( "border", "1px solid #d9d9d9");
		                	  ok4=true;
		                }
	               });
	               
	               
	               $("button[name='EditBtn']").click(
	            		   function(){
	            		       $("#form").attr("action", "/Market/api/SubmitEdit?id="+"${product.id}");
	            			   var result=false;
	            			  // document.getElementById("form").action="/Market/api/SubmitEdit?id="+"${product.id}";
	            			      
			            	   if(ok1 && ok2 && ok3 && ok4){
			            		   result=true;
			            	   }
			            	   return result;
	                      });
	               $("button[name='Cancel']").click(
	            		   function(){
	            		       $("#form").attr("action", "/Market/api/showAllProduct");
	                      });
	               
               });
           </script>
  </body>

</html>