package com.example.dialogalisa.service;

import com.example.dialogalisa.dto.model.ServiceUser;
import com.example.dialogalisa.dto.model.Session;
import com.example.dialogalisa.dto.yandexAlice.request.YASession;
import com.example.dialogalisa.dto.yandexAlice.request.YandexAliceRequest;
import com.example.dialogalisa.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;
    private YASession yandexSession;
    private Session session;

    public Session validateSession(YandexAliceRequest yandexAliceRequest, ServiceUser user){
        yandexSession = yandexAliceRequest.getSession();
        session = sessionRepository.getBySessionId(yandexSession.getSessionId()).orElse(null);
        if (session != null) {
            return session;
        }else {
            session = sessionRepository.getByServiceUser(user).orElse(null);
            if (session != null){
                session.setSessionId(yandexSession.getSessionId());
                return session;
            }
            return new Session(yandexAliceRequest.getSession().getSessionId());
        }
    }

    public Session saveSession(Session session) {
        return sessionRepository.save(session);
    }
}
