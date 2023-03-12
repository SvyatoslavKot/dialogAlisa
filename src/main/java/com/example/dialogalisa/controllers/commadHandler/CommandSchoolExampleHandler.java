package com.example.dialogalisa.controllers.commadHandler;

import com.example.dialogalisa.controllers.abstractClass.AbstractRequestHandler;
import com.example.dialogalisa.controllers.abstractClass.YandexAlisCommandHandler;
import com.example.dialogalisa.dto.model.Lesson;
import com.example.dialogalisa.dto.model.Session;
import com.example.dialogalisa.dto.model.SessionState;
import com.example.dialogalisa.dto.yandexAlice.request.YandexAliceRequest;
import com.example.dialogalisa.dto.yandexAlice.response.YandexAliceResponse;
import com.example.dialogalisa.service.LessonService;
import com.example.dialogalisa.service.SessionService;
import com.example.dialogalisa.service.UserService;
import com.example.dialogalisa.util.DateAndNumToString;

import java.util.List;
import java.util.Locale;


public class CommandSchoolExampleHandler extends AbstractRequestHandler implements YandexAlisCommandHandler {


    private YandexAliceResponse response;
    private DateAndNumToString dateParser = new DateAndNumToString();

    private List<Lesson> lessonsWithEx;
    private int day;

    public CommandSchoolExampleHandler(UserService userService, SessionService sessionService, LessonService lessonService) {
        super(userService, sessionService, lessonService);
    }

    @Override
    public YandexAliceResponse requestHandler(YandexAliceRequest yandexAliceRequest, Session session) {
        String command = yandexAliceRequest.getRequest().getCommand();
        command.toLowerCase(Locale.ROOT);
        String responseMsg  = new String();
        String responseTts = new String();
        day = dateParser.dayWeakFromString(command);

        if (day > 5) {
            response = createResponse("Завтра выходной, может быть ты хочешь узнать домашнее задание на понедельник?",
                    "<speaker effect=\"hamster\"> Завтра выходной, может быть ты хочешь узнать домашнее задание на понедельник?",
                    session,
                    SessionState.SCHOOL_EXAMPLE,
                    "домашнее задание на понедельник"
            );
            return response;
        }

        String dayOfWeak = dateParser.dayWeakToStringDeclensions(day);
        responseTts = responseTts + "<speaker effect=\"hamster\">";
        lessonsWithEx = lessonService.getLessonWithExampleByDayOfWeak(day);

        if (lessonsWithEx.size() > 0){
            responseMsg = responseMsg + "Я нашел домашнее задание на " + dayOfWeak ;
            responseTts = responseTts + "Я нашел домашнее задание на " + dayOfWeak + "sil <[500]>";
            for (Lesson l : lessonsWithEx) {
                responseMsg = responseMsg + "\n" + l.getTitle() +": " +  l.getExampleRecommended();
                responseTts = responseTts + l.getTitle() + "sil <[300]>" +  l.getExampleRecommended() + "sil <[600]>";
            }
            responseMsg = responseMsg + "\n" + "Может быть, что-то еще";
            responseTts = responseTts + "Может быть sil <[150]> что-то еще?";
        }else {
            responseMsg = responseMsg + "Я не нашел домашнего задания на " + dayOfWeak;
            responseTts = responseTts + "Я sil <[150]> не нашел домашнево задание на " + dayOfWeak + "sil <[500]>";
        }
        response = createResponse(responseMsg,responseTts,session,SessionState.COMMAND_END,"");
        return response;
    }
}
