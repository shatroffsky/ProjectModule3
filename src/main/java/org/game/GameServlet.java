package org.game;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // якщо прийшло ім’я з index.jsp
        String playerName = request.getParameter("playerName");
        if (playerName != null) {
            session.setAttribute("playerName", playerName);
            session.setAttribute("step", 1); // починаємо гру з першого питання
        }

        processGame(request, response, session);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        processGame(request, response, session);
    }

    private void processGame(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {

        Integer step = (Integer) session.getAttribute("step");
        if (step == null) step = 1;

        String choice = request.getParameter("choice");
        if (choice != null) {
            // Логіка сюжету
            if (step == 1) {
                if ("A".equals(choice)) {
                    step = 2;
                } else {
                    request.setAttribute("gameOver", "Ти вибрав невірно і програв!");
                }
            } else if (step == 2) {
                if ("B".equals(choice)) {
                    step = 3;
                } else {
                    request.setAttribute("gameOver", "Неправильний вибір. Кінець гри!");
                }
            } else if (step == 3) {
                if ("A".equals(choice)) {
                    step = 4;
                } else {
                    request.setAttribute("gameOver", "Тебе зловили! Гра закінчена.");
                }
            } else if (step == 4) {
                request.setAttribute("victory", "Вітаю! Ти пройшов гру 🎉");
            }
        }

        session.setAttribute("step", step);

        // передаємо дані у JSP
        request.setAttribute("step", step);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/game.jsp");
        dispatcher.forward(request, response);
    }
}
