package com.charlancodes.fashapi.service;

import com.charlancodes.fashapi.dtos.RequestDto.PersonDto;
import com.charlancodes.fashapi.dtos.ResponseDto.PersonLoginResponse;
import com.charlancodes.fashapi.dtos.ResponseDto.PersonResponseDto;
import com.charlancodes.fashapi.entity.BlogPost;

public interface AdminService {

    PersonResponseDto CreateNewAdmin(PersonDto personDto);
    PersonLoginResponse adminLogin(String email, String password);
}
