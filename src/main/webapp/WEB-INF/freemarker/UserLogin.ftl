<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">

<script type="text/javascript" src="../js/md5.js"></script>    
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script> 
<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>    
<script>
      function MD5Passwd()
      {
       	var x=document.getElementById("password").value;  // 找到元素
       	//document.write(hex_md5(x));
       	document.getElementById("txt_passwd").value=hex_md5(x);   // 改变内容
      }
</script>
</head>

<body>
<div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
                    请<a href="/Market/UserLogin.jsp">[登录]</a>
        </div>
        <ul class="nav">
            <li><a href="/Market/api/showAllProduct">首页</a></li>
        </ul>
    </div>
</div>
<form class="n-login m-form m-form-ht" action="/Market/api/login" method="post">
    <div class="fmitem">
        <label class="fmlab">用户名：</label>
        <div class="fmipt" >
            <input class="u-ipt" name="username" autofocus>
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab">密码：</label>
        <div class="fmipt" >
            <input class="u-ipt" type="password" name="password" id="password">
        </div>
    </div>
    <div class="fmitem-nolab">
        <div class="fmipt" >
            <button class="u-btn u-btn-primary u-btn-lg u-btn-block" type="submit" onclick="MD5Passwd()">登 录</button>
        </div>
    </div>
    <div class="fmitem-nolab">
	     <div class="fmipt">
	            <label style="color:red">${message}</label>
	     </div>
    </div>
   
    <div>  
           <input type="hidden" id="txt_passwd" name="passwd"/>  
    </div>
    
</form>
     
</body>
</html>