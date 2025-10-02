<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    Integer step = (Integer) request.getAttribute("step");
    String gameOver = (String) request.getAttribute("gameOver");
    String victory = (String) request.getAttribute("victory");
    String playerName = (String) session.getAttribute("playerName");
    org.game.model.Question question = (org.game.model.Question) request.getAttribute("question");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Гра</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="header">Пригоди героя</div>

<div class="story-box">
    <h2>Привіт, <%= playerName %>!</h2>

    <% if (gameOver != null) { %>
    <p><%= gameOver %></p>
    <a href="game?restart=true"><button>Почати з першого питання</button></a>
    <a href="index.jsp"><button>На головну</button></a>
    <% } else if (victory != null) { %>
    <p><%= victory %></p>
    <a href="game?restart=true"><button>Грати знову</button></a>
    <a href="index.jsp"><button>На головну</button></a>
    <% } else if (question != null) { %>
    <p><%= question.getText() %></p>
    <form method="get" action="game">
        <% for (java.util.Map.Entry<String, org.game.model.Choice> entry : question.getChoices().entrySet()) { %>
        <button type="submit" name="choice" value="<%= entry.getKey() %>">
            <%= entry.getValue().getText() %>
        </button>
        <% } %>
    </form>
    <% } %>
</div>
</body>
</html>
