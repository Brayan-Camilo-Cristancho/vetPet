package com.vetpet.apprest.persistence;

import com.vetpet.apprest.domain.dto.DoctorDto;
import com.vetpet.apprest.domain.repository.CrudRepository;
import com.vetpet.apprest.domain.repository.DoctorDtoRepository;
import com.vetpet.apprest.mapper.IdoctorMapper;
import com.vetpet.apprest.persistence.repository.DoctorPagSortRepository;
import com.vetpet.apprest.persistence.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoctorCrudRepositoryImp extends CrudRepository<DoctorDto> implements DoctorDtoRepository {
    private final DoctorRepository doctorRepository;
    private final DoctorPagSortRepository doctorPagSortRepository;
    private final IdoctorMapper idoctorMapper;

    @Autowired
    public DoctorCrudRepositoryImp(DoctorRepository doctorRepository, DoctorPagSortRepository doctorPagSortRepository, IdoctorMapper idoctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorPagSortRepository = doctorPagSortRepository;
        this.idoctorMapper = idoctorMapper;
    }

    @Override
    public List<DoctorDto> getAll() {
        return idoctorMapper.toDoctorsDto(doctorRepository.findAll());
    }

    @Override
    public void save(DoctorDto entity) {

    }

    @Override
    public void update(DoctorDto entity) {

    }

    @Override
    public void delete(String v) {

    }

    @Override
    public Page<DoctorDto> getAllOfPage() {
        Pageable pageableWithSorting = PageRequest.of(1, 3, Sort.by("lastName").ascending());

        return idoctorMapper.toDoctorsPageDto(doctorPagSortRepository.findByAgeBetween(20, 50, pageableWithSorting));
    }

    @Override
    public Slice<DoctorDto> getBySexAndAge() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("firstName").ascending());
        return idoctorMapper.toDoctorsSliceDto(doctorPagSortRepository.findByAgeGreaterThanAndSex(2, 'M', pageable));
    }
}
