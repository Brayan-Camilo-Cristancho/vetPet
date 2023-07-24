package com.vetpet.apprest.persistence.audit;

import com.vetpet.apprest.persistence.entity.PetEntity;
import jakarta.persistence.*;
import org.springframework.util.SerializationUtils;

public class AuditPetListener {
    private PetEntity currentValue;

    @PostLoad
    public void postLoad(PetEntity entity) {
        System.out.println("POST LOAD");
        this.currentValue = SerializationUtils.clone(entity);
    }

    @PostUpdate
    public void onPostUpdate(PetEntity entity) {
        System.out.println("POST UPDATE");
        System.out.println("OLD VALUE: " + this.currentValue.toString());
        System.out.println("NEW VALUE: " + entity.toString());
    }

    @PreRemove
    public void onPreDelete(PetEntity entity) {
        System.out.println("DELETE VALUE: " + entity.toString());

    }
}
