package com.vetpet.apprest.persistence;

import com.vetpet.apprest.domain.repository.UserLoginDtoRepository;
import com.vetpet.apprest.persistence.repository.UserLoginRepository;


public class UserLoginRepositoryImp implements UserLoginDtoRepository {
    private final UserLoginRepository userLoginRepository;

    public UserLoginRepositoryImp(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }


}
