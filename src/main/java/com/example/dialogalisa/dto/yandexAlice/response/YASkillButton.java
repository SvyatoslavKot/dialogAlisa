package com.example.dialogalisa.dto.yandexAlice.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class YASkillButton {
    String title;
    String url;
    String payload;
    @JsonIgnoreProperties
    boolean hide;
}
