package com.charlancodes.fashapi.service;

import com.charlancodes.fashapi.dtos.RequestDto.PersonDto;
import com.charlancodes.fashapi.dtos.ResponseDto.PersonLoginResponse;
import com.charlancodes.fashapi.dtos.ResponseDto.PersonResponseDto;
import com.charlancodes.fashapi.entity.Admin;
import com.charlancodes.fashapi.enums.Role;
import com.charlancodes.fashapi.exception.ResourceAlreadyAvailableException;
import com.charlancodes.fashapi.exception.ResourceNotExistException;
import com.charlancodes.fashapi.repository.AdminRepo;
import com.charlancodes.fashapi.utils.ModelMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final AdminRepo adminRepo;

    public AdminServiceImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }


    @Override
    public PersonResponseDto CreateNewAdmin(PersonDto personDto) {
        Optional<Admin> adminDb = adminRepo.findAdminByEmail(personDto.getEmail());
        if (adminDb.isPresent()) {
            throw new ResourceAlreadyAvailableException("Admin Already Exist");
        }
        Admin admin = new Admin();
        admin.setName(personDto.getName());
        admin.setEmail(personDto.getEmail());
        admin.setPassword(personDto.getPassword());
        admin.setRole(Role.ADMIN);
        Admin newAdmin = adminRepo.save(admin);
        PersonResponseDto personResponseDto = new PersonResponseDto();
        BeanUtils.copyProperties(newAdmin, personResponseDto);
        log.info(String.valueOf(personResponseDto));
        return personResponseDto;
    }
    @Override
    public PersonLoginResponse adminLogin(String email, String password){
        Admin adminchk = adminRepo.findAdminByEmailAndPassword(email, password).
                orElseThrow(()-> new ResourceNotExistException("Admin does not exist"));
        PersonLoginResponse personLoginResponse = new PersonLoginResponse();
        ModelMapperUtils.map(adminchk, personLoginResponse);
        return personLoginResponse;
    }

}
