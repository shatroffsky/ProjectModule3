package org.game.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.game.model.Question;

import javax.servlet.ServletContext;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class QuestionLoader {
    private static final String QUESTIONS_FILE = "/WEB-INF/questions.json";

    public static Map<Integer, Question> loadQuestions(ServletContext context) throws Exception {
        try (InputStream is = context.getResourceAsStream(QUESTIONS_FILE)) {
            ObjectMapper mapper = new ObjectMapper();
            Question[] questions = mapper.readValue(is, Question[].class);

            Map<Integer, Question> questionMap = new HashMap<>();
            for (Question q : questions) {
                questionMap.put(q.getStep(), q);
            }
            return questionMap;
        }
    }
}

