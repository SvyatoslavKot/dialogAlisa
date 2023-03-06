package com.example.dialogalisa.controllers;

import com.example.dialogalisa.controllers.abstractClass.YandexAlisRequestAbstractHandler;
import com.example.dialogalisa.dto.yandexAlice.request.YandexAliceRequest;
import com.example.dialogalisa.dto.yandexAlice.response.YandexAliceResponse;
import com.example.dialogalisa.service.LessonService;
import com.example.dialogalisa.service.SessionService;
import com.example.dialogalisa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/alice-webhook")
public class DialogController {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserService userService;
    @Autowired
    private LessonService lessonService;

    private YARequestHandlerFactory yaRequestHandlerFactory;

    @PostMapping
    public @ResponseBody YandexAliceResponse talkYandexAlice(@RequestBody YandexAliceRequest request) {
        yaRequestHandlerFactory = new YARequestHandlerFactory(userService,sessionService, lessonService);
        YandexAlisRequestAbstractHandler requestHandler = yaRequestHandlerFactory.newRequestHandler();

        return  requestHandler.requestHandler(request);
    }

}
