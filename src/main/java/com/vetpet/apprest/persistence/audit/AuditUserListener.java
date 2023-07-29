package com.vetpet.apprest.persistence.audit;

import com.vetpet.apprest.persistence.entity.UserEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditUserListener {

    private UserEntity currentValue;

    @PostLoad
    public void postLoad(UserEntity entity) {
        System.out.println("POST LOAD FROM USER");
        this.currentValue = SerializationUtils.clone(entity);

    }

    @PostUpdate
    public void onPostUpdate(UserEntity entity) {
        System.out.println("POST UPDATE FROM USER" );
        System.out.println("OLD VALUE: " + this.currentValue.toString());
        System.out.println("NEW VALUE: " + entity.toString());

    }

    @PreRemove
    public void onPreDelete(UserEntity entity) {
        System.out.println("DELETE VALUE FROM USER: " + entity.toString());
    }
}
