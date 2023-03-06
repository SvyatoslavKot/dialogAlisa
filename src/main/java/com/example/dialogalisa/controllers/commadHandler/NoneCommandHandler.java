package com.example.dialogalisa.controllers.commadHandler;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class NoneCommandHandler extends YandexAlisRequestAbstractHandler {

    public NoneCommandHandler(UserService userService, SessionService sessionService, LessonService lessonService, Session session) {
        super(userService, sessionService, lessonService, session);
    }

    @Override
    public YandexAliceResponse requestHandler(YandexAliceRequest yandexAliceRequest) {
        if (session.getState() != null) {
            return null;
        }else {
            String responseMsg = "Я не знаю этой команды попробуй задать свой вопрос немного иначе.";
            String responseTts = "<speaker effect=\"hamster\">Я не знаю этой команды sil <[600]> попробуй sil <[200]>задать свой вопрос sil <[600]> немного иначе.";
            String command = yandexAliceRequest.getRequest().getCommand();

            response = createResponse(responseMsg, responseTts,SessionState.NULLABLE, command );
            return response;
        }
    }

}
