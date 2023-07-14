package com.charlancodes.fashapi.service;

import com.charlancodes.fashapi.dtos.RequestDto.PersonDto;
import com.charlancodes.fashapi.dtos.ResponseDto.PersonLoginResponse;
import com.charlancodes.fashapi.dtos.ResponseDto.PersonResponseDto;

public interface CustomerService {
    PersonResponseDto CreateNewCustomer(PersonDto personDto);
    PersonLoginResponse customerLogin(String email, String password);
}
