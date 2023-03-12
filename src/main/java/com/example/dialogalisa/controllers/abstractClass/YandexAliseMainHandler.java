package com.example.dialogalisa.controllers.abstractClass;

import com.example.dialogalisa.dto.model.ServiceUser;
import com.example.dialogalisa.dto.yandexAlice.request.YandexAliceRequest;
import com.example.dialogalisa.dto.yandexAlice.response.YandexAliceResponse;

public interface YandexAliseMainHandler {

    YandexAliceResponse requestHandler(YandexAliceRequest yandexAliceRequest);
    YandexAliceResponse newSession (YandexAliceRequest request, ServiceUser user);
    YandexAliceResponse newUser (YandexAliceRequest request);
}
