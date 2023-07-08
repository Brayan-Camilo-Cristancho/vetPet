package com.vetpet.apprest.persistence.repository;

import com.vetpet.apprest.persistence.entity.PetEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;


public interface PetRepository extends ListCrudRepository<PetEntity, Long> {
    List<PetEntity> findBySpeciesOrderByName(String species);

    @Query("SELECT pet FROM PetEntity pet INNER JOIN pet.ownerEntity o WHERE o.firstName = :firstName AND o.lastName= :lastName AND o.email = :email")
    List<PetEntity> findByOwnerFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);
}
