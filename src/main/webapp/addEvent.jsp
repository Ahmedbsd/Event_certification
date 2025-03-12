<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Add Event</title>
</head>
<body>
    <h2>Add New Event</h2>

    <!-- Back to Event List -->
    <a href="eventList.jsp">
        <button>Back to Event List</button>
    </a>

    <!-- Add Event Form -->
    <form action="EventServlet" method="post" enctype="multipart/form-data">
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

        <label>Upload Participants CSV:</label>
        <input type="file" name="participantsFile" accept=".csv"><br>

        <input type="submit" value="Add Event">
    </form>
</body>
</html>
