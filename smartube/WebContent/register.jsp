<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>register</title>
</head>
<body>
<%@ page import ="java.sql.*" %>
<%
    String gname = request.getParameter("groupname");    
    String gkey = request.getParameter("groupkey");
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/number","root", "root");
    Statement st = con.createStatement();
    ResultSet rs;
    rs = st.executeQuery("select * from members where groupname='" + gname + "' and groupkey='" + gkey + "'");
    if (rs.next())
    {
        response.sendRedirect("html4.html");
    } else {
        out.println("Invalid password <a href='index.jsp'>try again</a>");
    }
%>

</body>
</html>