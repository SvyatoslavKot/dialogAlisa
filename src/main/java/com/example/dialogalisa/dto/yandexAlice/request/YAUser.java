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
public class YAUser {
    @JsonProperty("user_id")
    String userId;
    @JsonProperty("access_token")
    String accessToken;

}
