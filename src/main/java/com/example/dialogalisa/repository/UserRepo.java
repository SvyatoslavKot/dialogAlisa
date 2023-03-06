package com.example.dialogalisa.repository;

import com.example.dialogalisa.dto.model.ServiceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<ServiceUser, Long> {

    Optional<ServiceUser> getServiceUserByExtUserId(String userId);
}
