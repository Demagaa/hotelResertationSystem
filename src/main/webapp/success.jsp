<!DOCTYPE html>
<html>
<head>
    <title>Hotel Reservation System success page</title>
</head>
<body>

<h1>Hotel Reservation System</h1>
Success, room number  <%= request.getAttribute("RoomNum")  %> in <%= request.getAttribute("hotelName")%> Hotel is reserved
<br>
<a href="reserve.jsp">Make another reservation</a>
<a href="index.jsp">Main page</a>

</body>
</html>