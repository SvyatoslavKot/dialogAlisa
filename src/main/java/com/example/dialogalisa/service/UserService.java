package com.example.dialogalisa.service;

import com.example.dialogalisa.dto.model.ServiceUser;
import com.example.dialogalisa.dto.yandexAlice.request.YAUser;
import com.example.dialogalisa.dto.yandexAlice.request.YandexAliceRequest;
import com.example.dialogalisa.exception.SessionUserNullableException;
import com.example.dialogalisa.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public ServiceUser checkedUser(YandexAliceRequest yandexAliceRequest) {
        if (yandexAliceRequest.getSession().getUser() != null){
            return userRepo.getServiceUserByExtUserId(yandexAliceRequest.getSession().getUser().getUserId()).orElse(null);

        }
        throw new SessionUserNullableException("Session User return null");
    }

    public void saveUser(ServiceUser serviceUser){
        userRepo.save(serviceUser);
    }

    public ServiceUser getServiceUserByYAlisID (String extId) {
        ServiceUser user = userRepo.getServiceUserByExtUserId(extId).orElseThrow(() -> new RuntimeException("User not Found"));
        return user;
    }


}
