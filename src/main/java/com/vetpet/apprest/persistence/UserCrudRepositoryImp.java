package com.vetpet.apprest.persistence;

import com.vetpet.apprest.domain.dto.UserDto;
import com.vetpet.apprest.domain.repository.CrudRepository;
import com.vetpet.apprest.domain.repository.UserDtoRepository;
import com.vetpet.apprest.exceptions.InvalidRoleException;
import com.vetpet.apprest.exceptions.UserNotFoundException;
import com.vetpet.apprest.exceptions.ValuesNotFound;
import com.vetpet.apprest.mapper.IuserMapper;
import com.vetpet.apprest.persistence.entity.UserEntity;
import com.vetpet.apprest.persistence.entity.UserLoginEntity;
import com.vetpet.apprest.persistence.entity.UserRoleEntity;
import com.vetpet.apprest.persistence.repository.UserLoginRepository;
import com.vetpet.apprest.persistence.repository.UserPagSortRepository;
import com.vetpet.apprest.persistence.repository.UserRepository;
import com.vetpet.apprest.persistence.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class

UserCrudRepositoryImp extends CrudRepository<UserDto> implements UserDtoRepository {
    private final UserRepository userRepository;
    private final UserPagSortRepository userPagSortRepository;
    private final UserLoginRepository userLoginRepository;
    private final UserRoleRepository userRoleRepository;
    private final IuserMapper iuserMapper;

    @Autowired
    public UserCrudRepositoryImp(UserRepository userRepository, UserPagSortRepository userPagSortRepository, UserLoginRepository userLoginRepository, UserRoleRepository userRoleRepository, IuserMapper iuserMapper) {
        this.userRepository = userRepository;
        this.userPagSortRepository = userPagSortRepository;
        this.userLoginRepository = userLoginRepository;
        this.userRoleRepository = userRoleRepository;
        this.iuserMapper = iuserMapper;
    }

    @Override
    public List<UserDto> getAll() {
        return iuserMapper.toUsersDto(userRepository.findAll());
    }

    @Override
    @Transactional
    public Long save(UserDto entity, String roleA) {
        UserEntity user = iuserMapper.toUserEntity(entity);
        UserLoginEntity login = iuserMapper.toUserLoginEntity(entity);
        UserRoleEntity role = new UserRoleEntity();
        user.setAssigned_role(true);
        userRepository.save(user);

        login.setLocked(false);
        login.setDisabled(false);
        login.setUserId(user.getUserId());
        login.setUserEntity(user);

        userLoginRepository.save(login);

        role.setUsername(login.getUsername());
        role.setRole(roleA);

        role.setGrantedDate(LocalDateTime.now());
        userRoleRepository.save(role);
        return user.getUserId();
    }


    @Override
    public void update(UserDto user) {
        System.out.println(user.getUsername()+ "jusfadghfsdjkfsdgjfhsdkgfsdjh");
        Optional<UserLoginEntity> login = userLoginRepository.findByUsername(user.getUsername());
        UserEntity userEntity = iuserMapper.toUserEntity(user);
        login.ifPresentOrElse(userLoginEntity -> {
            userEntity.setUserId(userLoginEntity.getUserId());
            userEntity.setCreatedAt(userLoginEntity.getUserEntity().getCreatedAt());
            userEntity.setAssigned_role(userLoginEntity.getUserEntity().getAssigned_role());
            userRepository.save(userEntity);
        }, () -> {
            throw new UserNotFoundException("User not found");
        });

    }

    @Override
    @Transactional
    public void delete(String username) {
        UserRoleEntity userRoleEntity = userRoleRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User role not found for username: " + username));

        if (userRoleEntity.getRole().equals("customer")) {
            userRoleRepository.deleteByUsername(userRoleEntity.getUsername());
        } else {
            throw new InvalidRoleException("Cannot delete user with role other than 'customer'.");
        }
    }

    @Override
    public List<UserDto> findAllBySex(Character sex) {
        List<UserDto> userDtos = iuserMapper.toUsersDto(userRepository.findAllBySex(sex));
        if (userDtos.isEmpty()) {
            throw new ValuesNotFound("No data to display");
        }
        return userDtos;
    }

    @Override
    public UserDto findByEmailOrIdentification(String email, String identification) {
        return userRepository.findByEmailOrIdentification(email, identification).map(iuserMapper::toUserDto).orElseThrow(() -> new ValuesNotFound("No data to display"));
    }

    @Override
    public List<UserDto> findByPhone(String phone) {
        return userRepository.findByPhone(phone).stream().map(iuserMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    public Page<UserDto> getAllPage(Integer page, Integer size) {
        Pageable pageableWithSorting = PageRequest.of(page, size, Sort.by("firstName").ascending());
        return iuserMapper.toUsersPageDto(userPagSortRepository.findAll(pageableWithSorting));
    }

    @Override
    public void updateOther(UserDto user) {
        UserLoginEntity userUpdates = iuserMapper.toUserLoginEntity(user);
        Optional<UserLoginEntity> userLogin = userLoginRepository.findById(userUpdates.getUsername());
        userLogin.ifPresentOrElse(userLoginEntity -> {

            if (userUpdates.getLocked() != null) {
                userLoginEntity.setLocked(userUpdates.getLocked());
            }
            if (userUpdates.getDisabled() != null) {
                userLoginEntity.setDisabled(userUpdates.getDisabled());
            }
            userLoginRepository.save(userLoginEntity);

        }, () -> {
            throw new UserNotFoundException("User role not found for username: " + user.getUsername());
        });
    }
}
