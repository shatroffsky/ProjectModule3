package org.game;

import org.game.model.Question;
import org.game.service.GameResult;
import org.game.service.GameService;
import org.game.service.QuestionLoader;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private GameService gameService;

    @Override
    public void init() throws ServletException {
        try {
            Map<Integer, Question> questions = QuestionLoader.loadQuestions(getServletContext());
            gameService = new GameService(questions);
        } catch (Exception e) {
            throw new ServletException("Failed to load questions", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String playerName = request.getParameter("playerName");
        if (playerName != null) {
            session.setAttribute("playerName", playerName);
        }

        Integer step = (Integer) session.getAttribute("step");
        boolean restart = "true".equals(request.getParameter("restart"));
        String choice = request.getParameter("choice");

        GameResult result = gameService.processTurn(step, choice, restart);

        session.setAttribute("step", result.getStep());

        request.setAttribute("step", result.getStep());
        request.setAttribute("question", result.getQuestion());
        request.setAttribute("gameOver", result.getGameOverMessage());
        request.setAttribute("victory", result.getVictoryMessage());

        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }
}

