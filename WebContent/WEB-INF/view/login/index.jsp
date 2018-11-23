<%@ page language="java" deferredSyntaxAllowedAsLiteral="true" pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<style type="text/css">
	.formblock{
		margin-top: 180px;
		background-color: #ABDDE5;
		line-height: 15px;
		vertical-align: middle;
		
	}
	.formtext{
		padding-top: 200px;	
		vertical-align: middle;
		
	}
	.myform{
		margin-left: 100px;
	}
</style>
</head>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<!-- <link rel="stylesheet" href="bootstrap-4.0.0/dist/css/bootstrap.css" />
<script type="text/javascript" src="bootstrap-4.0.0/dist/js/bootstrap.js" ></script> -->
<link href="https://cdn.bootcss.com/bootstrap/4.1.1/css/bootstrap.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/bootstrap/4.1.1/js/bootstrap.js"></script>
<body class="container">
	<div class="row">
		<div class="col-md-7">
		
		</div>
		<div class="col-md-5">
			<div class="formblock">
				<div class="form-text">
					<h4 style="text-align: center;">用户登录</h4>
					<div class="myform">
						<form method="POST" action="login/dologin" role="form">				      
					            	<div class="form-group">
						                <label for="username">用户名：</label>
						                <input type="text" id="username" name="username" />
					              	</div>
					            	<div class="form-group">
						                <label for="password">密码：</label>
						                <input type="password" id="password" name="password" />
					               	</div>
					            	<div class="form-group" style="padding-left: 80px;">
					            		<input type="submit" value="登录" style="width: 100px;height: 30px;"/>
					            	</div>
					   </form>    
					</div>
				</div>
		  </div>
	</div>
</div>
 
</body>
</html>