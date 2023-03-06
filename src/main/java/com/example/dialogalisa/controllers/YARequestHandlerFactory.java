package com.example.dialogalisa.controllers;

import com.example.dialogalisa.controllers.abstractClass.YandexAlisRequestAbstractHandler;
import com.example.dialogalisa.controllers.commadHandler.CommandSchoolExampleHandler;
import com.example.dialogalisa.controllers.commadHandler.CommandSchoolScheduledHandler;
import com.example.dialogalisa.controllers.commadHandler.CommandSkillsHandler;
import com.example.dialogalisa.controllers.commadHandler.NoneCommandHandler;
import com.example.dialogalisa.controllers.sessionHandlers.*;
import com.example.dialogalisa.dto.model.Session;
import com.example.dialogalisa.dto.model.SessionState;
import com.example.dialogalisa.dto.yandexAlice.request.YandexAliceRequest;
import com.example.dialogalisa.service.LessonService;
import com.example.dialogalisa.service.SessionService;
import com.example.dialogalisa.service.UserService;

public class YARequestHandlerFactory {

    private UserService userService;
    private SessionService sessionService;
    private LessonService lessonService;

    public YARequestHandlerFactory(YandexAlisRequestAbstractHandler abstractHandler) {
        this.userService = abstractHandler.userService;
        this.sessionService = abstractHandler.sessionService;
        this.lessonService = abstractHandler.lessonService;
    }
    public YARequestHandlerFactory(UserService userService, SessionService sessionService, LessonService lessonService) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.lessonService = lessonService;
    }

    public YandexAlisRequestAbstractHandler newRequestHandler () {
        return new RequestHandler(userService,sessionService,lessonService);
    }

    public YandexAlisRequestAbstractHandler newRequestCommandHandler(Session session) {
        return new RequestCommandHandler(userService,sessionService,lessonService, session);
    }

    public YandexAlisRequestAbstractHandler newCommandSchoolScheduledHandler(Session session) {
        return new CommandSchoolScheduledHandler(userService, sessionService, lessonService, session);
    }

    public YandexAlisRequestAbstractHandler newCommandSchoolExampleHandler (Session session) {
        return new CommandSchoolExampleHandler(userService, sessionService, lessonService, session);
    }

    public YandexAlisRequestAbstractHandler newCommandSkillsHandler (Session session) {
        return  new CommandSkillsHandler(userService, sessionService, lessonService, session);
    }
    public YandexAlisRequestAbstractHandler newNoneCommandHandler (Session session) {
        return  new NoneCommandHandler(userService, sessionService, lessonService, session);
    }

    public YandexAlisRequestAbstractHandler newSessionHandler( Session session) {
        if (session.getState().equals(SessionState.FIRST_INITIAL)){
            return  new SessionFirstInfoHandler(userService, sessionService, lessonService, session);
        } else if (session.getState().equals(SessionState.COMMAND_END)) {
            return new SessionCommandEndHandler(userService, sessionService, lessonService, session);
        } else if (session.getState().equals(SessionState.INITIAL)) {
            return new SessionInitialHandler(userService, sessionService, lessonService, session);
        }else if (session.getState().equals(SessionState.INFO_SKILL)) {
            return new SessionInfoHandler(userService, sessionService, lessonService, session);
        }else if (session.getState().equals(SessionState.SCHOOL_SCHEDULED)) {
            return new SessionSchoolScheduledHandler(userService, sessionService, lessonService, session);
        }else if (session.getState().equals(SessionState.SCHOOL_EXAMPLE)) {
            return new SessionSchoolExampleHandler(userService, sessionService, lessonService, session);
        }else {
            return new SessionNoneCommandHandler(userService, sessionService, lessonService, session);
        }
    }
}
