package org.game.service;

import org.game.model.Question;

public class GameResult {
    private int step;
    private Question question;
    private String gameOverMessage;
    private String victoryMessage;

    public int getStep() { return step; }
    public void setStep(int step) { this.step = step; }

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }

    public String getGameOverMessage() { return gameOverMessage; }
    public void setGameOverMessage(String gameOverMessage) { this.gameOverMessage = gameOverMessage; }

    public String getVictoryMessage() { return victoryMessage; }
    public void setVictoryMessage(String victoryMessage) { this.victoryMessage = victoryMessage; }
}
