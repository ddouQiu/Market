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
        <form class="m-form m-form-ht " id="form" method="post" action="/Market/api/EditProduct?productName=${product.name}">
            <div class="fmitem" style="height:330px">
            <span class="imgpre bpImg"><img  id="imgpre" style="width:100%;height:100%" src=${product.picture}></span>
            <div class="text">    
                <h1>${product.name}</h1>
                <p style="color:red">${product.summary}</p>
                </br>
                <span>�۸�</span><span style="font-weight:bold;font-size: 18px"> ${product.price}Ԫ</span>
                
                <div class="fmitem fmitem-nolab fmitem-btn">
                <div class="fmipt" style="padding-top:30px;padding-left:20px">
                        <button type="submit" class="u-btn u-btn-primary u-btn-lg theme-login"  name="submit" style="font-size: 16px" >�༭</button>
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
        </div>
        </div>
            
    </body>
</html>