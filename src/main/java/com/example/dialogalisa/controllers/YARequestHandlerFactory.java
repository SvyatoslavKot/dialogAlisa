package com.example.dialogalisa.controllers;

import com.example.dialogalisa.controllers.abstractClass.YandexAlisCommandHandler;
import com.example.dialogalisa.controllers.abstractClass.YandexAliseMainHandler;
import com.example.dialogalisa.controllers.commadHandler.*;
import com.example.dialogalisa.controllers.sessionHandlers.*;
import com.example.dialogalisa.dto.model.Session;
import com.example.dialogalisa.dto.model.SessionState;
import com.example.dialogalisa.service.LessonService;
import com.example.dialogalisa.service.SessionService;
import com.example.dialogalisa.service.UserService;

public class YARequestHandlerFactory {


    public static YandexAliseMainHandler newMainRequestHandler (UserService userService, SessionService sessionService,
                                                         LessonService lessonService, YandexAlisCommandHandler commandHandler) {
        return new MainRequestHandler(userService,sessionService,lessonService, commandHandler);
    }

    public static YandexAlisCommandHandler newRequestCommandHandler(UserService userService, SessionService sessionService,
                                                                     LessonService lessonService) {
        return new RequestCommandHandler(userService,sessionService,lessonService);
    }

    public static YandexAlisCommandHandler newCommandSchoolScheduledHandler(UserService userService, SessionService sessionService,
                                                                            LessonService lessonService) {
        return new CommandSchoolScheduledHandler(userService, sessionService, lessonService);
    }

    public static YandexAlisCommandHandler newCommandSchoolExampleHandler (UserService userService, SessionService sessionService,
                                                                           LessonService lessonService) {
        return new CommandSchoolExampleHandler(userService, sessionService, lessonService);
    }

    public static YandexAlisCommandHandler newCommandSkillsHandler (UserService userService, SessionService sessionService,
                                                                    LessonService lessonService) {
        return  new CommandSkillsHandler(userService, sessionService, lessonService);
    }
    public static YandexAlisCommandHandler newNoneCommandHandler (UserService userService, SessionService sessionService,
                                                                  LessonService lessonService) {
        return  new NoneCommandHandler(userService, sessionService, lessonService);
    }

    public static YandexAlisCommandHandler newSessionHandler(UserService userService, SessionService sessionService,
                                                             LessonService lessonService, Session session) {
        if (session.getState().equals(SessionState.FIRST_INITIAL)){
            return  new SessionFirstInfoHandler(userService, sessionService, lessonService);
        } else if (session.getState().equals(SessionState.COMMAND_END)) {
            return new SessionCommandEndHandler(userService, sessionService, lessonService);
        } else if (session.getState().equals(SessionState.INITIAL)) {
            return new SessionInitialHandler(userService, sessionService, lessonService);
        }else if (session.getState().equals(SessionState.INFO_SKILL)) {
            return new SessionInfoHandler(userService, sessionService, lessonService);
        }else if (session.getState().equals(SessionState.SCHOOL_SCHEDULED)) {
            return new SessionSchoolScheduledHandler(userService, sessionService, lessonService);
        }else if (session.getState().equals(SessionState.SCHOOL_EXAMPLE)) {
            return new SessionSchoolExampleHandler(userService, sessionService, lessonService);
        }else {
            return new SessionNoneCommandHandler(userService, sessionService, lessonService);
        }
    }




}
