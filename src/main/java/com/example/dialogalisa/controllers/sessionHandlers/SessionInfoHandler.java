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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class SessionInfoHandler extends YandexAlisRequestAbstractHandler {

    public SessionInfoHandler(UserService userService, SessionService sessionService, LessonService lessonService, Session session) {
        super(userService, sessionService, lessonService, session);
    }

    @Override
    public YandexAliceResponse requestHandler(YandexAliceRequest yandexAliceRequest) {

        String originalText = yandexAliceRequest.getRequest().getCommand();
        String responseText = new String();
        String responseTts = new String();

        if (originalText.equals("спасибо") || originalText.equals("понял") || originalText.equals("понятно") || originalText.equals("ок") || originalText.equals("хорошо") ){
            responseText = responseText + "Обращайся, всегда буду готов помочь!";
            responseTts = responseTts + "<speaker effect=\"hamster\"> Обращайся sil <[500]> всегда буду готов помочь!";

            response = createResponse(responseText,responseTts);
            return response;

        }else if (originalText.contains("ага")|| originalText.contains("хочу") || originalText.contains("да") || originalText.contains("давай") || originalText.contains("расскажи") || originalText.contains("скажи") || originalText.contains("покажи")) {
            responseText = responseText + "Я могу показать расписание попроси 'покажи расписание на ...'. \n"
                            +"Или я сказать, что тебе задавали просто назови день. \n " +
                            "я могу не так много, но я еще учусь.";
            responseTts = responseTts + "<speaker effect=\"hamster\"> Я мог+у показ+ать распис+ание ур+оков sil <[500]> пр+осто скаж+и sil <[500]> 'покаж+и распис+ание на sil <[500]> ииии sil <[300]> день нед+ели sil <[300]> на кот+орый х+очешь узн+ать сво+ё распис+ание'. \n"
                            +"sil <[600]>Или ты м+ожешь спрос+ить,sil <[500]> что теб+е з+адали в шк+оле н+адом sil <[600]> скаж+и:sil<[300]> как+ая дом+ашка на з+автра?sil <[600]> ну или чт+о-то в этом р+оде . \n " +
                            "sil <[600]> я sil <[200]> мог+у не так мн+ого,sil <[600]> но я еще уч+усь.";
            response = createResponse(responseText,responseTts,SessionState.INFO_SKILL,"");
            return response;

        }else {
            responseText = responseText +"Не знаю.";
            responseTts = responseTts + "<speaker effect=\"hamster\"> Не знаю.";
            response = createResponse(responseText,responseTts);
            return response;
        }
    }

}
