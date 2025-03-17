<%@ page import="java.util.List" %>
<%@ page import="com.example.model.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Event List</title>
</head>
<body>
    <h2>Event Management</h2>

    <!-- Event List -->
    <h3>Event List</h3>
    <table border="1">
        <tr>
            <th>Title</th>
            <th>Description</th>
            <th>Date</th>
            <th>Period</th>
            
        </tr>
        <%
            List<Event> events = (List<Event>) request.getAttribute("events");
            if (events != null) {
                for (Event event : events) {
        %>
        <tr>
            <td><%= event.getTitle() %></td>
            <td><%= event.getDescription() %></td>
            <td><%= event.getDate() %></td>
            <td><%= event.getPeriod() %></td>
        </tr>
        <% } } %>
    </table>
</body>
</html>
	