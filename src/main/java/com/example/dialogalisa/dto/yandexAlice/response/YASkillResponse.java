package com.example.dialogalisa.dto.yandexAlice.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class YASkillResponse {
    @NonNull
    String text;

    String tts;

    YASkillCard card;

    List<YASkillButton> buttons;

    //YASKillDirectives directives;
    @JsonProperty("end_session")
    boolean endSession = false;
}
