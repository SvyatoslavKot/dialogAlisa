package com.example.dialogalisa.util;

public enum Phrases {
    SCHEDULED_END("Рядовой валера доклад закончил",
            "<speaker effect=\"hamster\"> Рядовой Валера sil <[450]> доклад закончил "),
    SCHEDULED_END_WEAK("Фуф, вроде все",
                          "<speaker effect=\"hamster\"> Фуф <[650]> , вроде всё. "),
    NONE_SESSION_COMMAND("хм даже не знаню, что на это ответить","<speaker effect=\"hamster\"> хм <[650]> даже не знаню, что на это ответить");

    private String text;
    private String tts;

    Phrases(String text, String tts) {
        this.text = text;
        this.tts = tts;
    }

    public String getText() {
        return text;
    }

    public String getTts() {
        return tts;
    }
}
