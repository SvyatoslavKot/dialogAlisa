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

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class YandexAlisRequestAbstractHandler implements YandexAlisResponsable {

    public UserService userService;
    public SessionService sessionService;
    public LessonService lessonService;

    public YASession yandexSession;
    public YandexAliceResponse response = new YandexAliceResponse(new YASkillResponse());
    public Session session;
    public ServiceUser user;
    public Date date = new Date();
    public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public YandexAlisRequestAbstractHandler(UserService userService, SessionService sessionService, LessonService lessonService) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.lessonService = lessonService;
        this.simpleDateFormat.format(date);
    }

    public YandexAlisRequestAbstractHandler(UserService userService, SessionService sessionService,LessonService lessonService, Session session) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.lessonService = lessonService;
        this.session = session;
        this.simpleDateFormat.format(date);
    }

    public YandexAlisRequestAbstractHandler() {
    }

    public YandexAlisRequestAbstractHandler(Session session) {
        this.session = session;
    }

    public YandexAliceResponse createResponse(String responseText, String responseTts, SessionState sessionState, String sessionText) {
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
