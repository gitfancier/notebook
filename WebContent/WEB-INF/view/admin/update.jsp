<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset="UTF-8">
<title>user register</title>
</head>
<body>
 <h2>修改信息</h2>
    <form:form method="POST" action="doupdate">
        <table>
        	<tr>
        		<td></td>
        		<td><input type="hidden" name="id" value="${user.id}"></td>
    		</tr>
            <tr>
                <td><label for="username">姓名：</label></td>
                <td><input type="username" id="username" name="username" value="${user.username}" /></td>
            </tr>
            <tr>
                <td><label for="password">密码：</label></td>
                <td><input type="password" id="password" name="password" value="${user.password}" /></td>
            </tr>
            <tr>
                <td><label for="truename">实名：</label></td>
                <td><input type="text" id="truename" name="truename" value="${user.truename}" /></td>
            </tr>
            <tr>
            	<td><input id="sex0" name="sex" type="radio" value="1" ${user.sex=='1'?'checked=true':''}/><label for="sex">男</label></td>
				<td><input id="sex1" name="sex" type="radio" value="0" ${user.sex=='0'?'checked=true':''}/><label for="sex1">女</label></td>
            </tr>
            <tr>
                <td><label for="email">邮箱：</label></td>
                <td><input type="text" id="email" name="email" value="${user.email}" /></td>
            </tr> 
            <tr>
                <td></td>
                <td><input type="hidden" id="create_time" name="create_time" value="${user.create_time}" /></td>
            </tr> 
            <tr>
                <td colspan="2"><input type="submit" value="注册" /></td>
            </tr>
        </table>
    </form:form>

</body>
</html>