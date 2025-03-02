<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.model.*" %>
<%@ page session="true" %>

<%
    // Retrieve user session
    User user = (User) session.getAttribute("user");

    // Redirect to login page if not logged in
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
</head>
<body>
    <h2>Welcome, <%= user.getFullName() %>!</h2>
    <p>Your role: <%= user.getType() %></p>

    <a href="logout.jsp">Logout</a>
</body>
</html>
