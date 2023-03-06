package com.example.dialogalisa.dto.yandexAlice.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class YandexAliceRequest {
    YAMetadata meta;
    YASkillRequest request;
    YASession session;
    String version;
}
