package com.example.dialogalisa.dto.yandexAlice.response;

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
public class YASkillCard {
    String type;
    @JsonProperty("image_id")
    String imageId;
    String title;
    String description;
    YASkillButton button;
}
