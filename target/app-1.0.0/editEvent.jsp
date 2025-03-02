<%@ page import="com.example.model.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Edit Event</title>
</head>
<body>
    <h2>Edit Event</h2>

    <%
        Event event = (Event) request.getAttribute("event");
        if (event != null) {
    %>
    <form action="EventServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= event.getId() %>">

        <label>Title:</label>
        <input type="text" name="title" value="<%= event.getTitle() %>" required><br>

        <label>Description:</label>
        <input type="text" name="description" value="<%= event.getDescription() %>" required><br>

        <label>Date:</label>
        <input type="date" name="date" value="<%= event.getDate() %>" required><br>

        <label>Period:</label>
        <select name="period">
            <option value="1 day" <%= event.getPeriod().equals("1 day") ? "selected" : "" %>>1 day</option>
            <option value="1 week" <%= event.getPeriod().equals("1 week") ? "selected" : "" %>>1 week</option>
            <option value="2 weeks" <%= event.getPeriod().equals("2 weeks") ? "selected" : "" %>>2 weeks</option>
            <option value="1 month" <%= event.getPeriod().equals("1 month") ? "selected" : "" %>>1 month</option>
        </select><br>

        <input type="submit" value="Update Event">
    </form>
    <% } %>

</body>
</html>
