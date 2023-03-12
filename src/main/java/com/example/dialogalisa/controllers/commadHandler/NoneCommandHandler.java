package com.example.dialogalisa.controllers.commadHandler;

import com.example.dialogalisa.controllers.abstractClass.AbstractRequestHandler;
import com.example.dialogalisa.controllers.abstractClass.YandexAlisCommandHandler;
import com.example.dialogalisa.dto.model.Session;
import com.example.dialogalisa.dto.model.SessionState;
import com.example.dialogalisa.dto.yandexAlice.request.YandexAliceRequest;
import com.example.dialogalisa.dto.yandexAlice.response.YandexAliceResponse;
import com.example.dialogalisa.service.LessonService;
import com.example.dialogalisa.service.SessionService;
import com.example.dialogalisa.service.UserService;


public class NoneCommandHandler extends AbstractRequestHandler implements YandexAlisCommandHandler {

    public NoneCommandHandler(UserService userService, SessionService sessionService, LessonService lessonService) {
        super(userService, sessionService, lessonService);
    }

    @Override
    public YandexAliceResponse requestHandler(YandexAliceRequest yandexAliceRequest, Session session) {
        if (session.getState() != null) {
            return null;
        }else {
            String responseMsg = "Я не знаю этой команды попробуй задать свой вопрос немного иначе.";
            String responseTts = "<speaker effect=\"hamster\">Я не знаю этой команды sil <[600]> попробуй sil <[200]>задать свой вопрос sil <[600]> немного иначе.";
            String command = yandexAliceRequest.getRequest().getCommand();

            response = createResponse(responseMsg, responseTts, session,SessionState.NULLABLE, command );
            return response;
        }
    }

}
