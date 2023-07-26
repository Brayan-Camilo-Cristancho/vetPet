package com.vetpet.apprest.domain.repository;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDtoRepository {
    UserDetails loadUserByUsername(String username);
}
