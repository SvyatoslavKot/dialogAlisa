package com.example.dialogalisa.controllers.commadHandler;

import com.example.dialogalisa.controllers.YARequestHandlerFactory;
import com.example.dialogalisa.controllers.abstractClass.AbstractRequestHandler;
import com.example.dialogalisa.controllers.abstractClass.YandexAlisCommandHandler;
import com.example.dialogalisa.dto.model.ServiceUser;
import com.example.dialogalisa.dto.model.Session;
import com.example.dialogalisa.dto.model.SessionState;
import com.example.dialogalisa.dto.yandexAlice.request.YandexAliceRequest;
import com.example.dialogalisa.dto.yandexAlice.response.YASkillButton;
import com.example.dialogalisa.dto.yandexAlice.response.YandexAliceResponse;
import com.example.dialogalisa.service.LessonService;
import com.example.dialogalisa.service.SessionService;
import com.example.dialogalisa.service.UserService;
import com.example.dialogalisa.util.YARequestUtil;

import java.util.List;
import java.util.Locale;

public class RequestCommandHandler extends AbstractRequestHandler implements YandexAlisCommandHandler {

    private YARequestUtil yaRequestUtil = new YARequestUtil();
    private YandexAlisCommandHandler handler;


    public RequestCommandHandler(UserService userService, SessionService sessionService, LessonService lessonService) {
        super(userService, sessionService, lessonService);
    }

    @Override
    public YandexAliceResponse requestHandler(YandexAliceRequest yandexAliceRequest, Session session) {
        System.out.println(yandexAliceRequest.getRequest().getCommand());

        String command = yandexAliceRequest.getRequest().getCommand();
        if(command!= null){
            command.toLowerCase(Locale.ROOT);

            if (command.contains("как") && command.contains("рассписание") && command.contains("загружать") || command.contains("загрузить") || command.contains("добавить")  ){
                YASkillButton button = new YASkillButton("Асистент Валера", "https://t.me/AssistantValeraRobot","",true);
                response.getResponse().setButtons(List.of(button));
                response.getResponse().setText("Для загрузки рассписания вы можете воспользоваться ассистентБотом в Телеграм.");
                response.getResponse().setTts("<speaker effect=\"hamster\"> Для загрузки рассписания sil <[600]> вы можете восп+ользоваться ассист+ент Б+отом в Телегр+ам.");
                return response;
            }else if (command.contains("расписание") || command.contains("уроки") || command.contains("рассписание") ){
                handler = YARequestHandlerFactory.newCommandSchoolScheduledHandler(this.userService, this.sessionService,this.lessonService);
                return handler.requestHandler(yandexAliceRequest, session);

            }else if (command.contains("задание") || command.contains("задания") || command.contains("домашка") || command.contains("задали")){
                handler = YARequestHandlerFactory.newCommandSchoolExampleHandler(this.userService, this.sessionService,this.lessonService);
                return handler.requestHandler(yandexAliceRequest, session);

            }else if (command.contains("help") || command.contains("помощь") || command.contains("умеешь") && command.contains("что") || command.contains("можешь") && command.contains("что") || command.contains("навыки") ){
                handler = YARequestHandlerFactory.newCommandSkillsHandler(this.userService, this.sessionService,this.lessonService);
                return handler.requestHandler(yandexAliceRequest, session);

            }else if (command.contains("сколько")){
                response = createResponse("Сегодня шесть уроков","Сегодня шесть уроков", session,SessionState.SCHOOL_SCHEDULED, command  );
                return response;

            }else if (yandexAliceRequest.getRequest().getCommand().contains("привет")){

                response.getResponse().setText( "Привет привет. Что вам надобно?");
                response.getResponse().setTts("<speaker effect=\"hamster\">  Привет привет. Что вам н+адобно?\n" +
                        "Узнать чего хотите? sil <[600]> или может спросить?");
                return response;
            }
            else if (command.contains("меня зовут") || command.contains("моё имя")){
                String name = yaRequestUtil.definedEntityFIO(yandexAliceRequest.getRequest());
                if (name != null) {
                    ServiceUser user = userService.getServiceUserByYAlisID(yandexAliceRequest.getSession().getUser().getUserId());
                    if (user!= null & !user.getName().equals(name)) {
                        user.setName(name);
                        userService.saveUser(user);
                    }
                    response.getResponse().setText("Очень приятно " + name + ",а меня Валера, для друзей просто Валерчик.");
                    response.getResponse().setTts("<speaker effect=\"hamster\"> ОООО+очень Приятно " + name + ",sil <[600]> а меня Вал+ера,sil <[400]> для друзей sil <[100]> просто sil <[200]> Вал+ерчик.\"");
                    return response;
                }
                response.getResponse().setText("Приятно, а меня Валера, для друзей просто Валерчик.");
                response.getResponse().setTts("<speaker effect=\"hamster\"> ОООО+очень Приятно,sil <[600]> а меня Вал+ера,sil <[400]> для друзей sil <[200]> просто sil <[200]> Вал+ерчик.\"");
                return response;


            }else {
                handler = YARequestHandlerFactory.newNoneCommandHandler(this.userService, this.sessionService,this.lessonService);
                return handler.requestHandler(yandexAliceRequest, session);
            }

        }else {
            return null;
        }
    }
}
