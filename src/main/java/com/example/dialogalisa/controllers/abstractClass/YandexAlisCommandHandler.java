package com.example.dialogalisa.controllers.abstractClass;

import com.example.dialogalisa.dto.model.Session;
import com.example.dialogalisa.dto.yandexAlice.request.YandexAliceRequest;
import com.example.dialogalisa.dto.yandexAlice.response.YandexAliceResponse;

public interface YandexAlisCommandHandler {

    YandexAliceResponse requestHandler(YandexAliceRequest yandexAliceRequest, Session session);
}
