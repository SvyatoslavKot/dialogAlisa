package com.example.dialogalisa.controllers.sessionHandlers;

import com.example.dialogalisa.controllers.YARequestHandlerFactory;
import com.example.dialogalisa.controllers.abstractClass.AbstractRequestHandler;
import com.example.dialogalisa.controllers.abstractClass.YandexAlisCommandHandler;
import com.example.dialogalisa.dto.model.Session;
import com.example.dialogalisa.dto.model.SessionState;
import com.example.dialogalisa.dto.yandexAlice.request.YandexAliceRequest;
import com.example.dialogalisa.dto.yandexAlice.response.YandexAliceResponse;
import com.example.dialogalisa.service.LessonService;
import com.example.dialogalisa.service.SessionService;
import com.example.dialogalisa.service.UserService;
import com.example.dialogalisa.util.YARequestUtil;


public class SessionFirstInfoHandler extends AbstractRequestHandler implements YandexAlisCommandHandler {


    private YARequestUtil requestUtil =  new YARequestUtil();
    private YandexAlisCommandHandler handler;

    public SessionFirstInfoHandler(UserService userService, SessionService sessionService, LessonService lessonService) {
        super(userService, sessionService, lessonService);
    }

    @Override
    public YandexAliceResponse requestHandler(YandexAliceRequest yandexAliceRequest, Session session) {

        String name = requestUtil.definedEntityFIO(yandexAliceRequest.getRequest());
        user = userService.checkedUser(yandexAliceRequest);
        if (user!= null) {
            user.setName(name);
            userService.saveUser(user);
            if (session.getWord() != null){
                response.getResponse().setText(
                        "Привет, " + name + "./n"+
                                "Ты можешь узнать что я умею, просто спроси и я отвечу ))");
                response.getResponse().setTts(
                        "<speaker effect=\"hamster\">"+
                                "Привет, sil <[300]>" + name + " sil <[700]>" +
                                "Ты можешь узнать,sil <[200]> что я умею, sil <[400]> просто спроси и я отвечу ))");
                return response;
            }else {
                yandexAliceRequest.getRequest().setCommand(session.getWord());
                session.setWord("");
                session.setState(SessionState.INITIAL);
                sessionService.saveSession(session);

                handler = YARequestHandlerFactory.newRequestCommandHandler(this.userService,this.sessionService,this.lessonService);
                YandexAliceResponse responseHandler =  handler.requestHandler(yandexAliceRequest, session);
                if (responseHandler != null) {
                    return  responseHandler;
                }else {
                    response = createResponse("Я не знаю такой команды, может ты хочешь узнать, что я умею?",
                            "<speaker effect=\"hamster\"> Я не зн+аю так+ой ком+анды,sil <[200]> м+ожет ты х+очешь узн+ать, что я ум+ею?",
                            session,
                            SessionState.INFO_SKILL,
                            "");
                    return response;
                }
            }

        }else {
            throw new NullPointerException();
        }
    }
}
