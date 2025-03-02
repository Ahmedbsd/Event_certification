<%@ page import="java.util.List" %>
<%@ page import="com.example.model.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Event Management</title>
</head>
<body>
    <h2>Event Management</h2>

    <!-- Add Event Form -->
    <h3>Add New Event</h3>
    <form action="EventServlet" method="post">
        <input type="hidden" name="action" value="add">
        <label>Title:</label>
        <input type="text" name="title" required><br>

        <label>Description:</label>
        <input type="text" name="description" required><br>

        <label>Date:</label>
        <input type="date" name="date" required><br>

        <label>Period:</label>
        <select name="period">
            <option value="1 day">1 day</option>
            <option value="1 week">1 week</option>
            <option value="2 weeks">2 weeks</option>
            <option value="1 month">1 month</option>
        </select><br>

        <input type="submit" value="Add Event">
    </form>

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
                <a href="editEvent.jsp?id=<%= event.getId() %>">Edit</a>
                <a href="EventServlet?action=delete&id=<%= event.getId() %>">Delete</a>
            </td>
        </tr>
        <% } } %>
    </table>
</body>
</html>
