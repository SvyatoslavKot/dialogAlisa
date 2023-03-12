package com.example.dialogalisa.controllers.sessionHandlers;

import com.example.dialogalisa.controllers.abstractClass.AbstractRequestHandler;
import com.example.dialogalisa.controllers.abstractClass.YandexAlisCommandHandler;
import com.example.dialogalisa.dto.model.Session;
import com.example.dialogalisa.dto.model.SessionState;
import com.example.dialogalisa.dto.yandexAlice.request.YandexAliceRequest;
import com.example.dialogalisa.dto.yandexAlice.response.YandexAliceResponse;
import com.example.dialogalisa.service.LessonService;
import com.example.dialogalisa.service.SessionService;
import com.example.dialogalisa.service.UserService;


public class SessionCommandEndHandler extends AbstractRequestHandler implements YandexAlisCommandHandler {

    public SessionCommandEndHandler(UserService userService, SessionService sessionService, LessonService lessonService) {
        super(userService, sessionService, lessonService);
    }

    @Override
    public YandexAliceResponse requestHandler(YandexAliceRequest yandexAliceRequest, Session session) {
        String command = yandexAliceRequest.getRequest().getCommand();
        String commandFromSession = session.getWord();
        String requestText = new String();
        String requestTts = new String();
        if (command.contains("да") || command.contains("ещё")) {
            requestText = requestText + "Что бы ты еще хотел узнать?";
            requestTts = requestTts + "<speaker effect=\"hamster\"> Что бы ты еще хотел узнать?";

            response = createResponse(requestText,requestTts,session,SessionState.NULLABLE, "");
            return response;
        }else if(command.contains("нет") || command.contains("не надо") || command.contains("неa") || command.contains("не") || command.contains("спасибо") || command.contains("ясно")){
            requestText = requestText + "Обращайся , всегда буду рад помочь.";
            requestTts = requestTts + "<speaker effect=\"hamster\"> Обращайся ,sil <[500]> всегда буду рад помочь.";

            response = createResponse(requestText,requestTts,session,SessionState.NULLABLE, "");
            return response;
        }else if(command.contains("почему")){
            if (commandFromSession != null && !commandFromSession.isEmpty()){
                requestText = requestText + "Потому что я не знаю, что ответить на \" " + commandFromSession + "\". " ;
                requestTts = requestTts + "<speaker effect=\"hamster\"> Потому что я не знаю, что ответить на \" " + commandFromSession + "\". ";

                response = createResponse(requestText,requestTts,session,SessionState.COMMAND_END, "");
                return response;
            }
            requestText = requestText + "Потому.";
            requestTts = requestTts + "<speaker effect=\"hamster\"> Потом+у";

            response = createResponse(requestText,requestTts,session,SessionState.COMMAND_END, "");
            return response;
        }else {
            requestText = requestText + "Не знаю, что ответить на это.";
            requestTts = requestTts + "<speaker effect=\"hamster\"> Не знаю, что ответить на это.";

            response = createResponse(requestText,requestTts,session,SessionState.COMMAND_END, command);
            return response;
        }
    }

}
