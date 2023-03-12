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

public class CommandSkillsHandler extends AbstractRequestHandler implements YandexAlisCommandHandler {

    public CommandSkillsHandler(UserService userService, SessionService sessionService, LessonService lessonService) {
        super(userService, sessionService, lessonService);
    }

    @Override
    public YandexAliceResponse requestHandler(YandexAliceRequest yandexAliceRequest, Session session) {
        String responseMsg = "Я могу показать расписание уроков просто скажи : 'покажи расписание на '(прим.) Понедельник'. \n"
                +"Или ты можешь спросить, что тебе задали в школе надом  скажи: (прим.) Какая домашка на завтра?. \n " +
                " я  могу не так много, но я еще учусь.";
        String responseTts = "<speaker effect=\"hamster\"> Я мог+у показ+ать распис+ание ур+оков sil <[500]> пр+осто скаж+и sil <[500]> 'покаж+и распис+ание на sil <[500]> ииии sil <[300]> день нед+ели sil <[300]> на кот+орый х+очешь узн+ать сво+ё распис+ание'. \n"
                +"sil <[600]>Или ты м+ожешь спрос+ить,sil <[500]> что теб+е з+адали в шк+оле н+адом sil <[600]> скаж+и:sil<[300]> как+ая дом+ашка на з+автра?sil <[600]> ну или чт+о-то в этом р+оде . \n " +
                "sil <[600]> я sil <[200]> мог+у не так мн+ого,sil <[600]> но я еще уч+усь.";

        response = createResponse(responseMsg, responseTts, session, SessionState.COMMAND_END,"");
        return response;
    }
}
