```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            text-align: center;
        }
        .header {
            background-color: green;
            color: white;
            font-size: 28px;
            font-weight: bold;
            padding: 30px;
            text-align: center;
        }
        .content {
            margin-top: 200px;
        }
        input[type="text"] {
            padding: 10px;
            width: 250px;
            font-size: 16px;
        }
        button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: green;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: darkgreen;
        }
    </style>
</head>
<body>
<div class="header">
    Ласкаво просимо до гри!
</div>

<div class="content">
    <form action="${pageContext.request.contextPath}/game" method="post">
    <input type="text" name="playerName" placeholder="Введіть своє ім'я" required>
        <br><br>
        <button type="submit">Почати</button>
    </form>
</div>
</body>
</html>
```
