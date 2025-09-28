```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String playerName = request.getParameter("playerName");
    if (playerName != null) {
        session.setAttribute("playerName", playerName);
    } else {
        playerName = (String) session.getAttribute("playerName");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Game</title>
</head>
<body>
    <h1>Привіт, <%= playerName %>! Починаємо гру 🚀</h1>
</body>
</html>
```
