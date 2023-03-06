package com.example.dialogalisa.controllers.sessionHandlers;

import com.example.dialogalisa.controllers.RequestCommandHandler;
import com.example.dialogalisa.controllers.YARequestHandlerFactory;
import com.example.dialogalisa.controllers.abstractClass.YandexAlisRequestAbstractHandler;
import com.example.dialogalisa.dto.model.ServiceUser;
import com.example.dialogalisa.dto.model.Session;
import com.example.dialogalisa.dto.model.SessionState;
import com.example.dialogalisa.dto.yandexAlice.request.YandexAliceRequest;
import com.example.dialogalisa.dto.yandexAlice.response.YASkillResponse;
import com.example.dialogalisa.dto.yandexAlice.response.YandexAliceResponse;
import com.example.dialogalisa.repository.SessionRepository;
import com.example.dialogalisa.repository.UserRepo;
import com.example.dialogalisa.service.LessonService;
import com.example.dialogalisa.service.SessionService;
import com.example.dialogalisa.service.UserService;
import com.example.dialogalisa.util.YARequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class SessionFirstInfoHandler extends YandexAlisRequestAbstractHandler {


    private YARequestUtil requestUtil =  new YARequestUtil();

    public SessionFirstInfoHandler(UserService userService, SessionService sessionService, LessonService lessonService, Session session) {
        super(userService, sessionService, lessonService, session);
    }

    @Override
    public YandexAliceResponse requestHandler(YandexAliceRequest yandexAliceRequest) {

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
                YARequestHandlerFactory factory = new YARequestHandlerFactory(this);
                YandexAlisRequestAbstractHandler handler = factory.newRequestCommandHandler(session);
                YandexAliceResponse responseHandler =  handler.requestHandler(yandexAliceRequest);
                if (responseHandler != null) {
                    return  responseHandler;
                }else {
                    response = createResponse("Я не знаю такой команды, может ты хочешь узнать, что я умею?",
                            "<speaker effect=\"hamster\"> Я не зн+аю так+ой ком+анды,sil <[200]> м+ожет ты х+очешь узн+ать, что я ум+ею?",
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
