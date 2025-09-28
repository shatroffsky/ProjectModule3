package org.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.game.model.Question;
import org.game.service.GameResult;
import org.game.service.GameService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private GameService gameService;

    @Override
    public void init() throws ServletException {
        try (InputStream is = getServletContext().getResourceAsStream("/WEB-INF/questions.json")) {
            ObjectMapper mapper = new ObjectMapper();
            Question[] questions = mapper.readValue(is, Question[].class);
            Map<Integer, Question> questionMap = new HashMap<>();
            for (Question q : questions) {
                questionMap.put(q.getStep(), q);
            }
            gameService = new GameService(questionMap);
        } catch (IOException e) {
            throw new ServletException("Не вдалося завантажити питання", e);
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

        // дані від користувача
        String playerName = request.getParameter("playerName");
        if (playerName != null) {
            session.setAttribute("playerName", playerName);
        }

        Integer step = (Integer) session.getAttribute("step");
        if (step == null) step = 1;

        boolean restart = "true".equals(request.getParameter("restart"));
        String choice = request.getParameter("choice");

        // викликаємо бізнес-логіку
        GameResult result = gameService.play(step, choice, restart);

        // оновлюємо сесію
        session.setAttribute("step", result.getStep());

        // передаємо дані в JSP
        request.setAttribute("step", result.getStep());
        request.setAttribute("question", result.getQuestion());
        request.setAttribute("gameOver", result.getGameOverMessage());
        request.setAttribute("victory", result.getVictoryMessage());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/game.jsp");
        dispatcher.forward(request, response);
    }
}

