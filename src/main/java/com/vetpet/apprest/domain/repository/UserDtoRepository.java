package com.vetpet.apprest.domain.repository;


import com.vetpet.apprest.domain.dto.UserDto;
import org.springframework.data.domain.Page;


import java.util.List;

public interface UserDtoRepository {
    List<UserDto> findAllBySex(Character sex);

    UserDto findByEmailOrIdentification(String email, String identification);

    List<UserDto> findByPhone(String phone);

    Page<UserDto> getAllPage(Integer page, Integer size);

}
