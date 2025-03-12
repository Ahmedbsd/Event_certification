<%@ page import="java.util.List" %>
<%@ page import="com.example.model.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Event List</title>
</head>
<body>
    <h2>Event Management</h2>

    <!-- Button to add a new event -->
    <a href="addEvent.jsp">
        <button>Add New Event</button>
    </a>

    <!-- Event List -->
    <h3>Event List</h3>
    <table border="1">
        <tr>
            <th>Title</th>
            <th>Description</th>
            <th>Date</th>
            <th>Period</th>
            <th>Actions</th>
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
            <td>
                <a href="EditEventServlet?id=<%= event.getId() %>">Edit</a>
                <a href="EventServlet?action=delete&id=<%= event.getId() %>">Delete</a>
            </td>
        </tr>
        <% } } %>
    </table>
</body>
</html>
	