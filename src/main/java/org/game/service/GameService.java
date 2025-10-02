package org.game.service;

import org.game.model.Question;
import org.game.model.Choice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class GameService {
    private static final Logger log = LoggerFactory.getLogger(GameService.class);

    private final Map<Integer, Question> questionMap;

    public GameService(Map<Integer, Question> questionMap) {
        this.questionMap = questionMap;
        log.info("GameService initialized with {} questions", questionMap.size());
    }

    public GameResult processTurn(Integer currentStep, String choice, boolean restart) {
        log.debug("Processing turn: currentStep={}, choice={}, restart={}", currentStep, choice, restart);
        if (currentStep == null || restart) {
            log.info("Starting/restarting the game");
            return startGame();
        }
        return play(currentStep, choice, false);
    }

    public GameResult play(int currentStep, String choice, boolean restart) {
        log.debug("Playing step={}, choice={}, restart={}", currentStep, choice, restart);

        GameResult result = new GameResult();
        int step = currentStep;

        if (choice != null) {
            Question q = questionMap.get(step);
            if (q != null) {
                log.debug("Found question at step {}: {}", step, q.getText());
                Choice ch = q.getChoices().get(choice);
                if (ch != null) {
                    log.debug("Player chose: {}", choice);
                    if (ch.getNext() != null) {
                        step = ch.getNext();
                        log.info("Moving to next step: {}", step);
                    } else if (ch.getEnd() != null) {
                        result.setGameOverMessage(ch.getEnd());
                        log.info("Game Over: {}", ch.getEnd());
                    } else if (ch.getVictory() != null) {
                        result.setVictoryMessage(ch.getVictory());
                        log.info("Victory: {}", ch.getVictory());
                    }
                } else {
                    log.warn("Invalid choice '{}' at step {}", choice, step);
                }
            } else {
                log.warn("No question found for step {}", step);
            }
        } else {
            log.debug("No choice provided at step {}", step);
        }

        result.setStep(step);
        result.setQuestion(questionMap.get(step));
        return result;
    }

    private GameResult startGame() {
        log.info("Game started at step 1");
        GameResult result = new GameResult();
        result.setStep(1);
        result.setQuestion(questionMap.get(1));
        return result;
    }
}
