<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Spring MVC表单处理(单选框)</title>
</head>
<body>

    <h2>提交用户信息 -</h2>
    <table>
        <tr>
            <td>用户名：</td>
            <td>${username}</td>
        </tr>
        <tr>
            <td>密码：</td>
            <td>${password}</td>
        </tr>
        <tr>
            <td>实名：</td>
            <td>${truename}</td>
        </tr>
        <tr>
            <td>性别：</td>
            <td>${(sex=="1"? "男" : "女")}</td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td>${email}</td>
        </tr>
        <tr>
            <td>创建时间：</td>
            <td>${create_time}</td>
        </tr>
        <tr>
            <td>更新时间：</td>
            <td>${update_time}</td>
        </tr>        
    </table>
</body>
</html>