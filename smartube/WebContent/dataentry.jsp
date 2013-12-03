<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>dataentry</title>
</head>
<body>
<%@ page import ="java.sql.*" %>
<%@ page import ="com.mysql.jdbc.Driver" %>
<% 
String language = request.getParameter("language");
String genre= request.getParameter("genre");
String artist = request.getParameter("artist");
String groupname=request.getParameter("groupname");

Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://vhost0057.site1.compute.ihost.com:3306/dataentry","root", "root");
    Statement st = con.createStatement();
    
    ResultSet rs;
    String sql = "insert into query(language,genre,artist,groupname) values ('" + language + "','" + genre + "','" + artist + "','"+groupname+"')";
    st.executeUpdate(sql);
    response.sendRedirect("dataprocess.jsp");
%>    
</body>
</html>