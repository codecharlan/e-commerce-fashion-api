package com.charlancodes.fashapi.repository;

import com.charlancodes.fashapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findAdminByEmail(String email);
    Optional<Customer>findAdminByEmailAndPassword(String email, String password);
}
