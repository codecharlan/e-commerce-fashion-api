package com.charlancodes.fashapi.model;

import com.charlancodes.fashapi.enums.Role;
import lombok.*;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class Person extends BaseIdClass {
    private String name;
    @Column(nullable = false, unique = true,length=45)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
