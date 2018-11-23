<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset="UTF-8">
<title>user register</title>
</head>
<body>
 <h2>注册信息</h2>
    <form:form method="POST" action="doregister">
        <table>
            <tr>
                <td><form:label path="username">姓名：</form:label></td>
                <td><form:input path="username" /></td>
            </tr>
            <tr>
                <td><form:label path="password">密码：</form:label></td>
                <td><form:password path="password" /></td>
            </tr>
            <tr>
                <td><form:label path="truename">实名：</form:label></td>
                <td><form:input path="truename" /></td>
            </tr>
            <tr>
            <td><form:label path="sex">性别：</form:label></td>
            <td>
                <form:radiobutton path="sex" value="1" label="男" />
                 <form:radiobutton path="sex" value="0" label="女" />
            </td>
            </tr>
            <tr>
                <td><form:label path="email">邮箱：</form:label></td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="注册" /></td>
            </tr>
        </table>
    </form:form>
</body>
</html>