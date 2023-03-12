package com.example.dialogalisa.controllers.abstractClass;

import com.example.dialogalisa.controllers.YARequestHandlerFactory;
import com.example.dialogalisa.dto.model.ServiceUser;
import com.example.dialogalisa.dto.model.Session;
import com.example.dialogalisa.dto.yandexAlice.request.YandexAliceRequest;
import com.example.dialogalisa.dto.yandexAlice.response.YandexAliceResponse;
import com.example.dialogalisa.exception.SessionUserNullableException;
import com.example.dialogalisa.service.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractMainRequestHandler extends AbstractRequestHandler implements YandexAliseMainHandler {

    private YandexAlisCommandHandler commandHandler;
    private YandexAlisCommandHandler sessionHandler;
    public Session session;
    private SessionProvider provider;

    public AbstractMainRequestHandler(UserService userService, SessionService sessionService, LessonService lessonService,
                                      YandexAlisCommandHandler commandHandler) {
        super(userService, sessionService, lessonService);
        this.commandHandler = commandHandler;
        this.provider = new SessionProviderJPARepo(sessionService,userService);
    }

    @Override
    public YandexAliceResponse requestHandler(YandexAliceRequest yandexAliceRequest) {
        log.info("Request => {}" , yandexAliceRequest);
        yandexSession = yandexAliceRequest.getSession();

        try{
            user = provider.checkedUser(yandexAliceRequest);
            session = provider.validateSession(yandexAliceRequest,user);

            if (user != null){
                if(!yandexAliceRequest.getRequest().getCommand().isEmpty()){
                    session.setServiceUser(user);
                    response = commandHandler.requestHandler(yandexAliceRequest,session);
                    if (response != null) {
                        return  response;
                    }else {
                        sessionHandler = YARequestHandlerFactory
                                .newSessionHandler(this.userService,this.sessionService,this.lessonService, session);
                        return sessionHandler.requestHandler(yandexAliceRequest, session);
                    }

                }
                else if (session.getState() != null){
                    sessionHandler = YARequestHandlerFactory
                            .newSessionHandler(this.userService,this.sessionService,this.lessonService, session);
                    return sessionHandler.requestHandler(yandexAliceRequest, session);
                }
                else{
                    return newSession(yandexAliceRequest, user);
                }
            }else {
                return newUser(yandexAliceRequest);
            }

        }catch (SessionUserNullableException e) {
            log.error(e.getMessage(), e.getCause());
            response.getResponse().setText("test");
            return response;
        }
    }
}
