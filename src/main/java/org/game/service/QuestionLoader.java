package org.game.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.game.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class QuestionLoader {
    private static final Logger log = LoggerFactory.getLogger(QuestionLoader.class);

    private static final String QUESTIONS_FILE = "/WEB-INF/questions.json";

    public static Map<Integer, Question> loadQuestions(ServletContext context) throws Exception {
        log.info("Loading questions from {}", QUESTIONS_FILE);

        try (InputStream is = context.getResourceAsStream(QUESTIONS_FILE)) {
            if (is == null) {
                log.error("Questions file '{}' not found in servlet context", QUESTIONS_FILE);
                throw new IllegalStateException("Questions file not found: " + QUESTIONS_FILE);
            }

            ObjectMapper mapper = new ObjectMapper();
            Question[] questions = mapper.readValue(is, Question[].class);

            Map<Integer, Question> questionMap = new HashMap<>();
            for (Question q : questions) {
                questionMap.put(q.getStep(), q);
                log.debug("Loaded question step={} text='{}'", q.getStep(), q.getText());
            }

            log.info("Successfully loaded {} questions", questionMap.size());
            return questionMap;
        } catch (Exception e) {
            log.error("Failed to load questions from {}", QUESTIONS_FILE, e);
            throw e;
        }
    }
}
