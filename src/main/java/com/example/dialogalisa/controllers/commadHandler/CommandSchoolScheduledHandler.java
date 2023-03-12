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
import com.example.dialogalisa.util.Phrases;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CommandSchoolScheduledHandler extends AbstractRequestHandler implements YandexAlisCommandHandler  {

    private DateAndNumToString dateAndNumParser = new DateAndNumToString();
    private int day;

    public CommandSchoolScheduledHandler(UserService userService, SessionService sessionService, LessonService lessonService) {
        super(userService, sessionService, lessonService);
    }

    @Override
    public YandexAliceResponse requestHandler(YandexAliceRequest yandexAliceRequest, Session session) {
        String command = yandexAliceRequest.getRequest().getCommand();
        if (command.contains("неделю")) {
            return responseHandlerWeak(response, session);
        } else {
            day = dateAndNumParser.dayWeakFromString(command);
            session.setWord(command);
        }
        return handler(response,session,day);
    }

    private YandexAliceResponse handler(YandexAliceResponse yandexAliceResponse, Session session,  int day) {
        String responseMsg  = new String();
        String responseTts = new String();
        if (day > 5) {
            response = createResponse("Завтра выходной, может быть ты хочешь узнать расписание на понедельник?",
                    " <speaker effect=\"hamster\"> Завтра выходной, может быть ты хочешь узнать расписание на понедельник?",
                    session,
                    SessionState.SCHOOL_SCHEDULED,
                    "покажи расписание на понедельник");
            return yandexAliceResponse;
        }
            String dayOfWeak = dateAndNumParser.dayWeakToString(day);
            List<Lesson> lessons = lessonService.getByDayOfWeak(day);

            LinkedList<Lesson> linkedListLesson = new LinkedList<>(
                    lessons.stream().sorted(Comparator.comparing(Lesson::getNumber)).collect(Collectors.toList()));

            responseTts = responseTts + "<speaker effect=\"hamster\">";

            responseMsg = responseMsg +"Ваше расписание на " + dayOfWeak + ": \n";
            responseTts = responseTts + "Ваше расписание на " + dayOfWeak + ":";
            for (Lesson lesson : linkedListLesson){
                responseMsg = responseMsg  + lesson.getNumber() + ") " + lesson.getTitle() + "\n";
                responseTts = responseTts + dateAndNumParser.numToStringTts(lesson.getNumber()) + ") " + lesson.getTitle() + "\n";
            }
            responseMsg = responseMsg + Phrases.SCHEDULED_END.getText();
            responseTts = responseTts + Phrases.SCHEDULED_END.getTts();

            response = createResponse(responseMsg, responseTts, session, SessionState.COMMAND_END, "");
            return response;
    }

    private YandexAliceResponse responseHandlerWeak(YandexAliceResponse yandexAliceResponse, Session session){
        String responseMsg  = new String();
        String responseTts = new String();
        Map<Integer, LinkedList<Lesson>> mapLesson = lessonService.getByWeak();
        responseTts =  responseTts + "<speaker effect=\"hamster\"> Ну слушай sil <[450]> .";
        for(int i = 1; i < 6; i ++) {
            LinkedList<Lesson> lessons = mapLesson.get(i);
            responseMsg = responseMsg + "\n" + dateAndNumParser.dayWeakToString(i);
            responseTts = responseTts + "\n" + dateAndNumParser.dayWeakToString(i);
            for (Lesson l : lessons) {
                responseMsg = responseMsg + "\n" + l.getNumber() + ") " + l.getTitle();
                responseTts = responseTts  + "\n" + dateAndNumParser.numToStringTts(l.getNumber()) + ") " + l.getTitle();
            }

        }
        responseMsg = responseMsg + Phrases.SCHEDULED_END_WEAK.getText();
        responseTts = responseTts + Phrases.SCHEDULED_END_WEAK.getTts();

        yandexAliceResponse = createResponse(responseMsg, responseTts, session, SessionState.COMMAND_END, "");
        return  yandexAliceResponse;
    }

}
