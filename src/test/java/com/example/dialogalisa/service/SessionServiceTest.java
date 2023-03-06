package com.example.dialogalisa.service;

import com.example.dialogalisa.dto.model.ServiceUser;
import com.example.dialogalisa.dto.model.Session;
import com.example.dialogalisa.dto.yandexAlice.request.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
//@DirtiesContext
class SessionServiceTest {

    //@Autowired
    SessionService sessionService;
    //@Autowired
    UserService userService;

    //@Test
    public void validateSessionTest(){
        YAMetadata metadata = new YAMetadata();
        metadata.setLocale("ru-RU");
        metadata.setTimezone("UTC");
        metadata.setClientId("ru.yandex.searchplugin/7.16 (none none; android 4.4.2)");

        YASkillRequest request = new YASkillRequest();
        request.setCommand("command");
        request.setMarkup(new YARequestMarkup(false));
        request.setNlu(new YANaturalLanguageUnderstanding(new ArrayList<>(),new ArrayList<>()));
        request.setType(YARequestType.SimpleUtterance);
        request.setOriginalUtterance("command");

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
        ServiceUser user = userService.checkedUser(yaRequest);

        Session sessionFromDB = sessionService.validateSession(yaRequest, user);
        System.out.println( "user => " + user + ", session => " + sessionFromDB);
    }
}