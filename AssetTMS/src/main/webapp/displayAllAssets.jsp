<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<%@ page import="com.wipro.asset.bean.AssetBean" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>All Asset Records</h2>
<%
List<AssetBean> list =
    (List<AssetBean>)request.getAttribute("list");
if(list == null || list.size()==0){
%>
    <h3>No records available!</h3>
<%
}else{
    for(AssetBean bean : list){
%>
<hr>
Asset ID: <%=bean.getAssetId()%><br>
Asset Name: <%=bean.getAssetName()%><br>
Asset Code: <%=bean.getAssetCode()%><br>
Purchase Date: <%=bean.getPurchaseDate()%><br>
Condition: <%=bean.getCondition()%><br>
Department: <%=bean.getDepartment()%><br>
Remarks: <%=bean.getRemarks()%><br>
<%
    }
}
%>
<a href="menu.html">Back to Menu</a>
</body>
</html>