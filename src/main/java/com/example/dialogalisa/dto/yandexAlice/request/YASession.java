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
public class YASession {
    @JsonProperty("message_id")
    int messageId;
    @JsonProperty("session_id")
    String sessionId;
    @JsonProperty("skill_id")
    String skillId;
    YAUser user;
    YAApplication application;
    boolean isNew;


}
