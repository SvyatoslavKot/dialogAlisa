package com.example.dialogalisa.controllers;

import com.example.dialogalisa.controllers.abstractClass.AbstractMainRequestHandler;
import com.example.dialogalisa.controllers.abstractClass.YandexAlisCommandHandler;
import com.example.dialogalisa.dto.model.ServiceUser;
import com.example.dialogalisa.dto.model.Session;
import com.example.dialogalisa.dto.model.SessionState;
import com.example.dialogalisa.dto.model.UserSource;
import com.example.dialogalisa.dto.yandexAlice.request.YASession;
import com.example.dialogalisa.dto.yandexAlice.request.YandexAliceRequest;
import com.example.dialogalisa.dto.yandexAlice.response.YASkillResponse;
import com.example.dialogalisa.dto.yandexAlice.response.YandexAliceResponse;
import com.example.dialogalisa.service.LessonService;
import com.example.dialogalisa.service.SessionService;
import com.example.dialogalisa.service.UserService;

public class MainRequestHandler extends AbstractMainRequestHandler {

    public MainRequestHandler(UserService userService, SessionService sessionService, LessonService lessonService,
                              YandexAlisCommandHandler commandHandler) {
        super(userService, sessionService, lessonService, commandHandler);
    }


    @Override
    public YandexAliceResponse newSession(YandexAliceRequest request, ServiceUser user) {
        session = new Session(SessionState.INITIAL,request.getSession().getSessionId());
        session.setServiceUser(user);
        sessionService.saveSession(session);
        response = createResponse("Привет, " + user.getName() +", давно не виделись.",
                "<speaker effect=\"hamster\"> Привет, sil <[200]>" + user.getName() +",sil <[600]> давно не виделись.");

        return response;
    }
    @Override
    public YandexAliceResponse newUser(YandexAliceRequest request) {
        YandexAliceResponse yandexAliceResponse = new YandexAliceResponse(new YASkillResponse());
        ServiceUser user = new ServiceUser();
        YASession yandexSession = request.getSession();
        user.setExtUserId(yandexSession.getUser().getUserId());
        user.setExtAppId(yandexSession.getApplication().getApplicationId());
        user.setUserSource(UserSource.YANDEX_ALICE);
        user.setLastUsed("1");

        Session session = new Session(SessionState.FIRST_INITIAL,request.getSession().getSessionId());
        session.setServiceUser(user);

        sessionService.saveSession(session);

        if (request.getRequest().getCommand().isEmpty()){
            yandexAliceResponse.getResponse().setText("Привет, я помошник Валера, давай знакомиться. Напиши скажи мне свое имя и потом я расскажу что я умею.");
            yandexAliceResponse.getResponse().setTts("<speaker effect=\"hamster\"> Привет! sil <[700]> я sil <[400]> помошник Валера,sil <[600]> давай знакомиться. sil <[700]> Скажи мне sil <[200]> свое имя sil <[300]>и потом я расскажу, sil <[300]> что я умею.");
        }else {
            session.setWord(request.getRequest().getCommand());
            yandexAliceResponse.getResponse().setText("Привет, я помошник Валера, давай для начала познакомимся. Скажи или напиши мне свое имя, а потом я отвечу на твою просьбу.");
            yandexAliceResponse.getResponse().setTts("<speaker effect=\"hamster\"> Привет,sil <[700]> я sil <[400]> помошник Валера,sil <[600]> давай для начала познакомимся. sil <[700]> Скажи или напиши sil <[200]>  мне свое имя,sil <[300]> а потом я отвечу на твою просьбу.");
        }
        return yandexAliceResponse;
    }


}
