package com.example.dialogalisa.controllers.abstractClass;

import com.example.dialogalisa.dto.model.ServiceUser;
import com.example.dialogalisa.dto.model.Session;
import com.example.dialogalisa.dto.model.SessionState;
import com.example.dialogalisa.dto.yandexAlice.request.YandexAliceRequest;
import com.example.dialogalisa.dto.yandexAlice.response.YASkillResponse;
import com.example.dialogalisa.dto.yandexAlice.response.YandexAliceResponse;

public interface YandexAlisResponsable {

    YandexAliceResponse requestHandler(YandexAliceRequest yandexAliceRequest);

}
