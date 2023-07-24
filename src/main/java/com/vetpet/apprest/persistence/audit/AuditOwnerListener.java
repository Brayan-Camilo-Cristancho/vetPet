package com.vetpet.apprest.persistence.audit;

import com.vetpet.apprest.persistence.entity.OwnerEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditOwnerListener {

    private OwnerEntity currentValue;


    @PostLoad
    public void postLoad(OwnerEntity entity) {
        System.out.println("POST LOAD");
        this.currentValue = SerializationUtils.clone(entity);

    }

    @PostUpdate
    public void onPostUpdate(OwnerEntity entity) {
        System.out.println("POST UPDATE");
        System.out.println("OLD VALUE: " + this.currentValue.toString());
        System.out.println("NEW VALUE: " + entity.toString());

    }

    @PreRemove
    public void onPreDelete(OwnerEntity entity) {
        System.out.println("DELETE VALUE: " + entity.toString());

    }
}
