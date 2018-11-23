<%@ page language="java" deferredSyntaxAllowedAsLiteral="true" pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>写日记</title>
</head>
<body>
<form method="POST" action="dowrite">
   <table> 
      <tr>
         <td><label for="write">笔记：</label></td>
         <td><input type="text" id="write" name="write" /></td>
      </tr>  
      <tr>
         <td><label for="encrypt">加密内容？</label></td>
         <td><input type="checkbox" id="encrypt" name="encrypt" /></td>
      </tr> 
      <tr>
         <td><label for="secret">加密密码：</label></td>
         <td><input type="text" id="secret" name="secret" /></td>
      </tr> 
      <tr>
         <td colspan="2">
            <input type="submit" value="提交"/>
         </td>
      </tr>
   </table>  
</form>
</body>
</html>