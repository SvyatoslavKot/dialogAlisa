package com.example.dialogalisa.dto.yandexAlice.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class YandexAliceResponse {
    @NonNull
    YASkillResponse response;
    @NonNull
    String version = "1.0";

}
