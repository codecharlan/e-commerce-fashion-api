package com.charlancodes.fashapi.controller;

import com.charlancodes.fashapi.dtos.RequestDto.PersonDto;
import com.charlancodes.fashapi.dtos.RequestDto.PersonLoginDto;
import com.charlancodes.fashapi.dtos.ResponseDto.PersonLoginResponse;
import com.charlancodes.fashapi.dtos.ResponseDto.PersonResponseDto;
import com.charlancodes.fashapi.service.CustomerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("api/fash")
public class CostumerController {

    private final CustomerServiceImpl userService;
    private final HttpSession httpSession;

    public CostumerController(CustomerServiceImpl userService, HttpSession httpSession) {
        this.userService = userService;
        this.httpSession = httpSession;
    }


    @PostMapping("create_user")
    public ResponseEntity<?> createCustomerRole(@Valid @RequestBody PersonDto personDto) {
        PersonResponseDto user = userService.CreateNewCustomer(personDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("user_login")
    public ResponseEntity<?> customerLogin(@Valid @RequestBody PersonLoginDto personLoginDto) {
        PersonLoginResponse customer = userService.customerLogin(personLoginDto.getEmail(), personLoginDto.getPassword());
        httpSession.setAttribute("user_id", customer.getId());
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
