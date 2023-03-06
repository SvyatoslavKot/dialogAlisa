package com.example.dialogalisa.util;

public enum PhrasesNoneComand {
    NONE_SESSESION(1, Phrases.NONE_SESSION_COMMAND);

    private int num;
    private Phrases phrases;

    PhrasesNoneComand(int num, Phrases phrases) {
        this.num = num;
        this.phrases = phrases;
    }

    public int getNum() {
        return num;
    }

    public Phrases getPhrases() {
        return phrases;
    }
}
