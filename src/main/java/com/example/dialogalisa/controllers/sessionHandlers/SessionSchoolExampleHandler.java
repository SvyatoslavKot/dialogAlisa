package com.example.dialogalisa.controllers.sessionHandlers;

import com.example.dialogalisa.controllers.RequestCommandHandler;
import com.example.dialogalisa.controllers.YARequestHandlerFactory;
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
import org.springframework.stereotype.Service;


public class SessionSchoolExampleHandler extends YandexAlisRequestAbstractHandler {

    private YARequestHandlerFactory factory;
    private YandexAlisRequestAbstractHandler handler;

    public SessionSchoolExampleHandler(UserService userService, SessionService sessionService, LessonService lessonService, Session session) {
        super(userService, sessionService, lessonService, session);
    }


    @Override
    public YandexAliceResponse requestHandler(YandexAliceRequest yandexAliceRequest) {

        String commandFromSession = session.getWord();
        String command = yandexAliceRequest.getRequest().getCommand();
        String responseText = new String();
        String responseTts = new String();

        if (command.contains("хорошо") || command.contains("ага") || command.contains("да") || command.contains("давай") || command.contains("ок") ||  command.contains("окей")  ||  command.contains("покажи")) {
            factory = new YARequestHandlerFactory(this);
            handler = factory.newRequestCommandHandler(session);
            yandexAliceRequest.getRequest().setCommand(commandFromSession);
            return handler.requestHandler(yandexAliceRequest);
        }else if(command.contains("нет") || command.contains("не надо") || command.contains("неa") || command.contains("не")){
            responseText = responseText + "Хорошо , может быть что-то еще?";
            responseTts = responseTts + "<speaker effect=\"hamster\"> Хорошо sil <[300]>, может быть что-то еще?";

            response = createResponse(responseText, responseTts,SessionState.NULLABLE, "");
            return response;
        }else {
            responseText = responseText + "Ясно, что-то еще?";
            responseTts = responseTts + "<speaker effect=\"hamster\"> Ясно sil <[100]>, что-то еще?";

            response = createResponse(responseText,responseTts,SessionState.NULLABLE,"");
            return response;
        }
    }

}
