package org.game.service;

import org.game.model.Question;
import org.game.model.Choice;

import java.util.Map;

public class GameService {
    private final Map<Integer, Question> questionMap;

    public GameService(Map<Integer, Question> questionMap) {
        this.questionMap = questionMap;
    }

    public GameResult processTurn(Integer currentStep, String choice, boolean restart) {
        if (currentStep == null || restart) {
            return startGame();
        }
        return play(currentStep, choice, false);
    }

    public GameResult play(int currentStep, String choice, boolean restart) {
        GameResult result = new GameResult();

        int step = currentStep;
        if (choice != null) {
            Question q = questionMap.get(step);
            if (q != null) {
                Choice ch = q.getChoices().get(choice);
                if (ch != null) {
                    if (ch.getNext() != null) {
                        step = ch.getNext();
                    } else if (ch.getEnd() != null) {
                        result.setGameOverMessage(ch.getEnd());
                    } else if (ch.getVictory() != null) {
                        result.setVictoryMessage(ch.getVictory());
                    }
                }
            }
        }

        result.setStep(step);
        result.setQuestion(questionMap.get(step));
        return result;
    }

    private GameResult startGame() {
        GameResult result = new GameResult();
        result.setStep(1);
        result.setQuestion(questionMap.get(1));
        return result;
    }
}
