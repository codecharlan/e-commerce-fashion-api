package com.charlancodes.fashapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class BaseIdClass {
    @Id
    @SequenceGenerator(name = "id", sequenceName = "id", allocationSize = 1)
    @GeneratedValue(generator = "id", strategy = GenerationType.SEQUENCE)
    private Long id;
}
