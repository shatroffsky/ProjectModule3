package org.game.model;

public class Choice {
    private Integer next;
    private String end;
    private String victory;
    private String text;

    public Integer getNext() { return next; }
    public void setNext(Integer next) { this.next = next; }
    public String getEnd() { return end; }
    public void setEnd(String end) { this.end = end; }
    public String getVictory() { return victory; }
    public void setVictory(String victory) { this.victory = victory; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
