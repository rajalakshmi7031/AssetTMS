<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.wipro.asset.bean.AssetBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Asset Details</h2>
<%
 AssetBean bean = (AssetBean) request.getAttribute("bean");
if(bean == null){
%>
    <h3>No matching records exists! Please try again!</h3>
<%
} else {
%>
Asset ID: <%=bean.getAssetId()%><br><br>
Asset Name: <%=bean.getAssetName()%><br><br>
Asset Code: <%=bean.getAssetCode()%><br><br>
Purchase Date: <%=bean.getPurchaseDate()%><br><br>
Condition: <%=bean.getCondition()%><br><br>
Department: <%=bean.getDepartment()%><br><br>
Remarks: <%=bean.getRemarks()%><br><br>
<%
}
%>
<a href="menu.html">Back to Menu</a>
</body>
</html>