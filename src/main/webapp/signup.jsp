<form action="signup" method="post">
    <input type="text" name="fullname" placeholder="Full Name" required><br>
    <input type="email" name="email" placeholder="Email" required><br>
    <input type="password" name="password" placeholder="Password" required><br>
    <input type="text" name="cin" placeholder="CIN" required><br>
    <select name="type">
        <option value="admin">Admin</option>
        <option value="participant">Participant</option>
        <option value="manager">Manager</option>
    </select><br>
    <button type="submit">Sign Up</button>
</form>
