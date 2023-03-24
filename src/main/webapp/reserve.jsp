<!DOCTYPE html>
<html>
<head>
    <title>Hotel Reservation System reserve page</title>
</head>
<body>

<h1>Hotel Reservation System</h1>
<p>Please choose your trip:</p>

<form method="post">
    <label for="reservationStart">Start:</label>
    <input type="date" id="reservationStart" name="reservationStart">
    <br>
    <label for="reservationEnd">End:</label>
    <input type="date" id="reservationEnd" name="reservationEnd">
    <input type="submit" value="NEXT"
           formaction="redirect.html">
</form>
</body>
</html>