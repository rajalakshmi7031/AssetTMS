<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Add Asset</h2>
<form action="MainServlet" method="post">
<input type="hidden" name="operation" value="newRecord"/>
Asset Name: <input type="text" name="assetName"/><br><br>
Asset Code: <input type="text" name="assetCode"/><br><br>
Purchase Date: <input type="date" name="purchaseDate"/><br><br>
Condition:
<select name="condition">
<option value="New">New</option>
<option value="Good">Good</option>
<option value="Damaged">Damaged</option>
</select><br><br>
Department: <input type="text" name="department"/><br><br>
Remarks: <input type="text" name="remarks"/><br><br>
<input type="submit" value="Add Asset"/>
</form>
</body>
</html>