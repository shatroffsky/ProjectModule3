<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    Integer step = (Integer) request.getAttribute("step");
    String gameOver = (String) request.getAttribute("gameOver");
    String victory = (String) request.getAttribute("victory");
    String playerName = (String) session.getAttribute("playerName");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Гра</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="header">
    Пригоди героя
</div>

<div class="story-box">
    <h2>Привіт, <%= playerName %>!</h2>

    <% if (gameOver != null) { %>
    <p><%= gameOver %></p>
    <a href="index.jsp"><button>Спробувати ще раз</button></a>
    <% } else if (victory != null) { %>
    <p><%= victory %></p>
    <a href="index.jsp"><button>Грати знову</button></a>
    <% } else if (step != null) { %>
    <% if (step == 1) { %>
    <p>Ти прокинувся в темному лісі. Попереду дві стежки.</p>
    <form method="get" action="game">
        <button type="submit" name="choice" value="A">Піти ліворуч</button>
        <button type="submit" name="choice" value="B">Піти праворуч</button>
    </form>
    <% } else if (step == 2) { %>
    <p>Ти зустрів річку. Що робиш?</p>
    <form method="get" action="game">
        <button type="submit" name="choice" value="A">Спробувати переплисти</button>
        <button type="submit" name="choice" value="B">Шукати міст</button>
    </form>
    <% } else if (step == 3) { %>
    <p>Ти побачив замок. Увійти чи обійти?</p>
    <form method="get" action="game">
        <button type="submit" name="choice" value="A">Увійти</button>
        <button type="submit" name="choice" value="B">Обійти</button>
    </form>
    <% } else if (step == 4) { %>
    <p>Ти дістався до скарбу! 🎉</p>
    <form method="get" action="game">
        <button type="submit" name="choice" value="A">Взяти скарб</button>
    </form>
    <% } %>
    <% } %>
</div>
</body>
</html>
