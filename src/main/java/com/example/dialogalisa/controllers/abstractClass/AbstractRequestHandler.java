package com.example.dialogalisa.controllers.abstractClass;


import com.example.dialogalisa.dto.model.ServiceUser;
import com.example.dialogalisa.dto.model.Session;
import com.example.dialogalisa.dto.model.SessionState;
import com.example.dialogalisa.dto.yandexAlice.request.YASession;
import com.example.dialogalisa.dto.yandexAlice.response.YASkillResponse;
import com.example.dialogalisa.dto.yandexAlice.response.YandexAliceResponse;
import com.example.dialogalisa.service.LessonService;
import com.example.dialogalisa.service.SessionService;
import com.example.dialogalisa.service.UserService;


public abstract class AbstractRequestHandler{

    public UserService userService;
    public SessionService sessionService;
    public LessonService lessonService;

    public YASession yandexSession;
    public ServiceUser user;
    public YandexAliceResponse response = new YandexAliceResponse(new YASkillResponse());


    public AbstractRequestHandler(UserService userService, SessionService sessionService, LessonService lessonService) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.lessonService = lessonService;
    }

    public YandexAliceResponse createResponse(String responseText, String responseTts, Session session, SessionState sessionState, String sessionText) {
        response.getResponse().setText(responseText);
        response.getResponse().setTts(responseTts);
        session.setWord(sessionText);
        session.setState(sessionState);
        sessionService.saveSession(session);
        return response;
    }

    public YandexAliceResponse createResponse(String responseText, String responseTts) {
        response.getResponse().setText(responseText);
        response.getResponse().setTts(responseTts);
        return response;
    }
}
