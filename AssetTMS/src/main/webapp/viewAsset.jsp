<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>View Asset</h2>
<form action="MainServlet" method="post">
<input type="hidden" name="operation" value="viewRecord"/>
Asset Code: <input type="text" name="assetCode"/><br><br>
Purchase Date: <input type="date" name="purchaseDate"/><br><br>
<input type="submit" value="View"/>
</form>
</body>
</html>