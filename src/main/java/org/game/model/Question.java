package org.game.model;

import java.util.Map;

public class Question {
    private int step;
    private String text;
    private Map<String, Choice> choices;

    public int getStep() { return step; }
    public void setStep(int step) { this.step = step; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public Map<String, Choice> getChoices() { return choices; }
    public void setChoices(Map<String, Choice> choices) { this.choices = choices; }
}
