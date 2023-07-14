package com.charlancodes.fashapi.controller;

import com.charlancodes.fashapi.dtos.RequestDto.PersonDto;
import com.charlancodes.fashapi.dtos.RequestDto.PersonLoginDto;
import com.charlancodes.fashapi.dtos.ResponseDto.PersonLoginResponse;
import com.charlancodes.fashapi.dtos.ResponseDto.PersonResponseDto;
import com.charlancodes.fashapi.service.AdminServiceImpl;
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
public class AdminController {
    private final AdminServiceImpl adminService;
    private final HttpSession httpSession;

    public AdminController(AdminServiceImpl adminService, HttpSession httpSession) {
        this.adminService = adminService;
        this.httpSession = httpSession;
    }


    @PostMapping("create_admin")
    public ResponseEntity<?> createAdminRole(@Valid @RequestBody PersonDto personDto) {
        PersonResponseDto admin = adminService.CreateNewAdmin(personDto);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PostMapping("admin_login")
    public ResponseEntity<?> adminLogin(@Valid @RequestBody PersonLoginDto personLoginDto) {
        PersonLoginResponse admin = adminService.adminLogin(personLoginDto.getEmail(), personLoginDto.getPassword());
        httpSession.setAttribute("admin_id", admin.getId());
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

}
