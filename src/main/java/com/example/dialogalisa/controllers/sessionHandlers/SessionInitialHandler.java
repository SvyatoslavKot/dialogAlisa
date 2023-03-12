package com.example.dialogalisa.controllers.sessionHandlers;

import com.example.dialogalisa.controllers.abstractClass.AbstractRequestHandler;
import com.example.dialogalisa.controllers.abstractClass.YandexAlisCommandHandler;
import com.example.dialogalisa.dto.model.Session;
import com.example.dialogalisa.dto.yandexAlice.request.YandexAliceRequest;
import com.example.dialogalisa.dto.yandexAlice.response.YandexAliceResponse;
import com.example.dialogalisa.service.LessonService;
import com.example.dialogalisa.service.SessionService;
import com.example.dialogalisa.service.UserService;


public class SessionInitialHandler extends AbstractRequestHandler implements YandexAlisCommandHandler {

    public SessionInitialHandler(UserService userService, SessionService sessionService, LessonService lessonService) {
        super(userService, sessionService, lessonService);
    }

    @Override
    public YandexAliceResponse requestHandler(YandexAliceRequest yandexAliceRequest, Session session) {
        response.getResponse().setText("Ты можешь спросить у меня что-нибудь, я попробую ответить. \n" +
                "Если не занешь, что спросить просто спроси у меня, что я умею");
        response.getResponse().setTts("<speaker effect=\"hamster\"> Ты можешь sil <[200]> спросить у меня что-нибудь, sil <[700]> я попробую ответить. \n" +
                "sil <[500]> А если не занешь, что спросить sil <[500]>, то просто спроси у меня :sil <[500]> что я умею ");
        return response;
    }

}
