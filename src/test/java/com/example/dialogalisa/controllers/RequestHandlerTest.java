package com.example.dialogalisa.controllers;

import com.example.dialogalisa.controllers.abstractClass.YandexAlisCommandHandler;
import com.example.dialogalisa.controllers.abstractClass.YandexAliseMainHandler;
import com.example.dialogalisa.dto.yandexAlice.request.*;
import com.example.dialogalisa.dto.yandexAlice.response.YandexAliceResponse;
import com.example.dialogalisa.service.LessonService;
import com.example.dialogalisa.service.SessionService;
import com.example.dialogalisa.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;


@SpringBootTest
@DirtiesContext
class RequestHandlerTest {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserService userService;
    @Autowired
    private LessonService lessonService;


    @Test
    public void testRequestHandler() {
        commandMethod("домашка на завтра" );

    }

    private void commandMethod(String command) {
        YAMetadata metadata = new YAMetadata();
        metadata.setLocale("ru-RU");
        metadata.setTimezone("UTC");
        metadata.setClientId("ru.yandex.searchplugin/7.16 (none none; android 4.4.2)");

        YASkillRequest request = new YASkillRequest();
        request.setCommand(command);
        request.setMarkup(new YARequestMarkup(false));
        request.setNlu(new YANaturalLanguageUnderstanding(new ArrayList<>(),new ArrayList<>()));
        request.setType(YARequestType.SimpleUtterance);
        request.setOriginalUtterance(command);

        YASession session = new YASession();
        session.setSessionId("a4569f8b-19c1-440a-9782-5920798bdba8");
        session.setSkillId("ea4015ee-57b1-44f1-ad80-f5eeeefa232c");
        YAUser yaUser = new YAUser("56A3F0718FDD60B0C6EA2F3FE850625B0D9D91391117001C60BC23571D8872FB", null);
        session.setUser(yaUser);
        YAApplication yaApplication = new YAApplication("ED2FB4B2DCB1255D054092FF09FFDBF0303A10269DAE0F9D0D5349CB877894D1");
        session.setApplication(yaApplication);
        session.setSessionId("ed9b78f3-15ba-4d9b-9cbd-d3e6b1ad216e");
        session.setNew(true);

        YandexAliceRequest yaRequest = new YandexAliceRequest(metadata,request,session,"1.0");

        YandexAlisCommandHandler commandHandler = YARequestHandlerFactory.newRequestCommandHandler(userService,sessionService,lessonService);
        YandexAliseMainHandler mainHandler = YARequestHandlerFactory.newMainRequestHandler(userService,sessionService,lessonService, commandHandler);
        YandexAliceResponse response = mainHandler.requestHandler(yaRequest);

        System.out.println(response.getResponse().getText());
    }
}