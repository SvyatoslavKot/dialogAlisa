package com.example.dialogalisa.dto.yandexAlice.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class YANaturalLanguageUnderstanding {
    List<String> tokens = new ArrayList<>();
    List<YAEntity> entities = new ArrayList<>();

}
