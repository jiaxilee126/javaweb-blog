<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
	<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<h2>Hello World!</h2>
	<form action="/qiniu/upload">
		<input type="file" />
		<button type="submit">提交</button>
	</form>
</body>
</html>
