package com.charlancodes.fashapi.service;

import com.charlancodes.fashapi.dtos.RequestDto.PersonDto;
import com.charlancodes.fashapi.dtos.ResponseDto.PersonLoginResponse;
import com.charlancodes.fashapi.dtos.ResponseDto.PersonResponseDto;
import com.charlancodes.fashapi.entity.Customer;
import com.charlancodes.fashapi.enums.Role;
import com.charlancodes.fashapi.exception.ResourceAlreadyAvailableException;
import com.charlancodes.fashapi.exception.ResourceNotExistException;
import com.charlancodes.fashapi.repository.CustomerRepo;
import com.charlancodes.fashapi.utils.ModelMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public PersonResponseDto CreateNewCustomer(PersonDto personDto) {
        Optional<Customer> userDb = customerRepo.findAdminByEmail(personDto.getEmail());
        if (userDb.isPresent()) {
            throw new ResourceAlreadyAvailableException("Customer Already Exist");
        }
        Customer customer = new Customer();
        customer.setName(personDto.getName());
        customer.setEmail(personDto.getEmail());
        customer.setPassword(personDto.getPassword());
        customer.setRole(Role.CUSTOMER);
        Customer newCustomer = customerRepo.save(customer);
        PersonResponseDto personResponseDto = new PersonResponseDto();
        ModelMapperUtils.map(newCustomer, personResponseDto);
        log.info(String.valueOf(personResponseDto));
        return personResponseDto;
    }

    @Override
    public PersonLoginResponse customerLogin(String email, String password) {
        Customer customerchk = customerRepo.findAdminByEmailAndPassword(email, password).
                orElseThrow(()-> new ResourceNotExistException("Customer does not exist"));
        PersonLoginResponse personLoginResponse = new PersonLoginResponse();
        ModelMapperUtils.map(customerchk, personLoginResponse);
        return personLoginResponse;
    }

}
