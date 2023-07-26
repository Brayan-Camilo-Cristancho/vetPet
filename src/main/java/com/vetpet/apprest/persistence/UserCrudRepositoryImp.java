package com.vetpet.apprest.persistence;

import com.vetpet.apprest.domain.repository.UserDtoRepository;
import com.vetpet.apprest.mapper.IuserMapper;
import com.vetpet.apprest.persistence.entity.UserEntity;
import com.vetpet.apprest.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserCrudRepositoryImp implements UserDtoRepository {
    private final UserRepository userRepository;
    private final IuserMapper iuserMapper;
    @Autowired
    public UserCrudRepositoryImp(UserRepository userRepository, IuserMapper iuserMapper) {
        this.userRepository = userRepository;
        this.iuserMapper = iuserMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles("ADMIN")
                .accountLocked(userEntity.getLocked())
                .disabled(userEntity.getDisabled())
                .build();
    }
}
