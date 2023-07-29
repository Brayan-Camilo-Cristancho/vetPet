package com.vetpet.apprest.persistence.audit;


import com.vetpet.apprest.persistence.entity.UserLoginEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostUpdate;

import org.springframework.util.SerializationUtils;

public class AuditUserLoginListener {
    private UserLoginEntity currentValue;

    @PostLoad
    public void postLoad(UserLoginEntity entity) {
        System.out.println("POST LOAD FROM USER LOGIN");
        this.currentValue = SerializationUtils.clone(entity);

    }

    @PostUpdate
    public void onPostUpdate(UserLoginEntity entity) {
        System.out.println("POST UPDATE FROM USER LOGIN");
        System.out.println("OLD VALUE: " + this.currentValue.toString());
        System.out.println("NEW VALUE: " + entity.toString());

    }

}
