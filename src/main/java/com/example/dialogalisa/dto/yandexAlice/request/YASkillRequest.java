package com.example.dialogalisa.dto.yandexAlice.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class YASkillRequest {
    String command;
    @JsonProperty("original_utterance")
    String originalUtterance;
    YARequestType type;
    YARequestMarkup markup;
    YANaturalLanguageUnderstanding nlu;

}
