<%@ page language="java" deferredSyntaxAllowedAsLiteral="true" pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户管理</title>
</head>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<link href="https://cdn.bootcss.com/bootstrap/4.1.1/css/bootstrap.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/bootstrap/4.1.1/js/bootstrap.js"></script>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2>用户管理</h2>
        </div>
    </div>
    <!--按钮-->
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <a class="btn btn-primary" href="register">新增</a>
        </div>
    </div>
    <!--显示表格数据-->
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <tr>
                    <th>id</th>
                    <th>username</th>
                    <th>password</th>
                    <th>truename</th>
                    <th>sex</th>
                    <th>email</th>
                    <th>create_time</th>
					<th>update_time</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${pageInfo.list}" var="user">
                    <tr>	
                        <th>${user.id}</th>
                        <th>${user.username}</th>
                        <th>${user.password}</th>
                    	<th>${user.truename}</th>
	                    <th>${user.sex==1?"男":"女" }</th>
                        <th>${user.email}</th>
                        <th>${user.create_time}</th>
                        <th>${user.update_time}</th> 
                        <th>
                            <a class="btn btn-primary btn-sm" href="update?id=${user.id}">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑
                            </a>
                        </th>
                        <th>    
                            <a class="btn btn-danger btn-sm" href="delete?id=${user.id}">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
                            </a>
                            </div>
                        </th>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <!--显示分页信息-->
    <div class="row">
        <!--文字信息-->
        <div class="col-md-6">
            当前第 ${pageInfo.pageNum} 页.总共 ${pageInfo.pages} 页.一共 ${pageInfo.total} 条记录
        </div>
        <!--点击分页-->
        <div class="col-md-6">
            <nav aria-label="Page navigation">
                <ul class="pagination">                   
                    <li><a class="btn btn-default" href="${pageContext.request.contextPath}/user/selectAll?pn=1">首页</a></li>                
                    <!--上一页-->
                    <li>
                        <c:if test="${pageInfo.hasPreviousPage}">
                            <a href="${pageContext.request.contextPath}/user/selectAll?pn=${pageInfo.pageNum-1}" aria-label="Previous">
                                <span aria-hidden="true">«</span>
                            </a>
                        </c:if>
                    </li>
                    <!--循环遍历连续显示的页面，若是当前页就高亮显示，并且没有链接-->
                    <c:forEach items="${pageInfo.navigatepageNums}" var="page_num">
                        <c:if test="${page_num == pageInfo.pageNum}">
                            <li class="btn btn-default" class="active"><a href="#">${page_num}</a></li>
                        </c:if>
                        <c:if test="${page_num != pageInfo.pageNum}">
                            <li><a class="btn btn-default" href="${pageContext.request.contextPath}/user/selectAll?pn=${page_num}">${page_num}</a></li>
                        </c:if>
                    </c:forEach>
                    <!--下一页-->
                    <li>
                        <c:if test="${pageInfo.hasNextPage}">
                            <a class="btn btn-default" href="${pageContext.request.contextPath}/user/selectAll?pn=${pageInfo.pageNum+1}"
                               aria-label="Next">
                                <span aria-hidden="true">»</span>
                            </a>
                        </c:if>
                    </li>
                    <li><a class="btn btn-default" href="${pageContext.request.contextPath}/user/selectAll?pn=${pageInfo.pages}">尾页</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
</html>