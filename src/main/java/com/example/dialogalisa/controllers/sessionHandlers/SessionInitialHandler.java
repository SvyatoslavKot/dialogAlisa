package com.example.dialogalisa.controllers.sessionHandlers;

import com.example.dialogalisa.controllers.abstractClass.YandexAlisRequestAbstractHandler;
import com.example.dialogalisa.dto.model.Session;
import com.example.dialogalisa.dto.yandexAlice.request.YandexAliceRequest;
import com.example.dialogalisa.dto.yandexAlice.response.YASkillResponse;
import com.example.dialogalisa.dto.yandexAlice.response.YandexAliceResponse;
import com.example.dialogalisa.service.LessonService;
import com.example.dialogalisa.service.SessionService;
import com.example.dialogalisa.service.UserService;
import org.springframework.stereotype.Service;


public class SessionInitialHandler extends YandexAlisRequestAbstractHandler {

    public SessionInitialHandler(UserService userService, SessionService sessionService, LessonService lessonService, Session session) {
        super(userService, sessionService, lessonService, session);
    }

    @Override
    public YandexAliceResponse requestHandler(YandexAliceRequest yandexAliceRequest) {
        response.getResponse().setText("Ты можешь спросить у меня что-нибудь, я попробую ответить. \n" +
                "Если не занешь, что спросить просто спроси у меня, что я умею");
        response.getResponse().setTts("<speaker effect=\"hamster\"> Ты можешь sil <[200]> спросить у меня что-нибудь, sil <[700]> я попробую ответить. \n" +
                "sil <[500]> А если не занешь, что спросить sil <[500]>, то просто спроси у меня :sil <[500]> что я умею ");
        return response;
    }

}
