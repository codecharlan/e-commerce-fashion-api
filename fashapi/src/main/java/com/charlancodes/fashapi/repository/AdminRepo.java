package com.charlancodes.fashapi.repository;

import com.charlancodes.fashapi.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin, Long> {
    Optional<Admin> findAdminByEmail(String email);
    Optional<Admin>findAdminByEmailAndPassword(String email, String password);
}
