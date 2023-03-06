package com.example.dialogalisa.repository;

import com.example.dialogalisa.dto.model.ServiceUser;
import com.example.dialogalisa.dto.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> getBySessionId(String sessionId);


    Optional<Session> getByServiceUser(ServiceUser user);
}
