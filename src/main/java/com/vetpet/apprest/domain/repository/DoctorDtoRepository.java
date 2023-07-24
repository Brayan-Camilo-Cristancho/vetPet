package com.vetpet.apprest.domain.repository;

import com.vetpet.apprest.domain.dto.DoctorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

public interface DoctorDtoRepository {
    Page<DoctorDto> getAllOfPage();
    Slice<DoctorDto> getBySexAndAge();
}
