<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>registerlogin</title>
</head>
<body>
<%@ page import ="java.sql.*" %>
<%@ page import ="com.mysql.jdbc.Driver" %>
<%
String fname = request.getParameter("firstname");
String lname= request.getParameter("lastname");
String gname = request.getParameter("groupname");
String gkey = request.getParameter("groupkey");
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/number","root", "root");
Statement st = con.createStatement();
//ResultSet rs;
int i = st.executeUpdate("insert into members(firstname,lastname,groupname,groupkey ) values ('" + fname + "','" + lname + "','" + gname + "','" + gkey + "')");
if (i > 0) {
response.sendRedirect("html4.html");
// out.<span class="adtext" id="adtext_3">print</span>("Registration Successfull!"+"<a href='index.jsp'>Go to Login</a>");
} else {
response.sendRedirect("index.jsp");
}
%>
</body>
</html>