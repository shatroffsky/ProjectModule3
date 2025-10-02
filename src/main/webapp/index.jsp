<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="header">
    Ласкаво просимо до гри!
</div>

<div class="story-box">
    Ти — герой, що прокинувся у загадковому світі.
    Попереду на тебе чекають небезпечні випробування і важливі вибори.
    Введи своє ім’я та почни пригоду прямо зараз!
</div>

<div class="content">
    <form action="${pageContext.request.contextPath}/game" method="post">
        <label>
            <input type="text" name="playerName" placeholder="Введіть своє ім'я" required>
        </label>
        <br><br>
        <button type="submit">Почати</button>
    </form>
</div>
</body>
</html>
