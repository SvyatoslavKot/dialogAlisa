package com.example.dialogalisa.controllers.sessionHandlers;

import com.example.dialogalisa.controllers.abstractClass.YandexAlisRequestAbstractHandler;
import com.example.dialogalisa.dto.model.Session;
import com.example.dialogalisa.dto.model.SessionState;
import com.example.dialogalisa.dto.yandexAlice.request.YandexAliceRequest;
import com.example.dialogalisa.dto.yandexAlice.response.YASkillResponse;
import com.example.dialogalisa.dto.yandexAlice.response.YandexAliceResponse;
import com.example.dialogalisa.repository.SessionRepository;
import com.example.dialogalisa.service.LessonService;
import com.example.dialogalisa.service.SessionService;
import com.example.dialogalisa.service.UserService;
import com.example.dialogalisa.util.Phrases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class SessionNoneCommandHandler extends YandexAlisRequestAbstractHandler {

    public SessionNoneCommandHandler(UserService userService, SessionService sessionService, LessonService lessonService, Session session) {
        super(userService, sessionService, lessonService, session);
    }

    @Override
    public YandexAliceResponse requestHandler(YandexAliceRequest yandexAliceRequest) {
        response = createResponse(Phrases.NONE_SESSION_COMMAND.getText(),
                Phrases.NONE_SESSION_COMMAND.getTts(),SessionState.NULLABLE, "" );

        return response;
    }

}
