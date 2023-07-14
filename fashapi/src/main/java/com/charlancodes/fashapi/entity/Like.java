package com.charlancodes.fashapi.entity;

import com.charlancodes.fashapi.enums.Reaction;
import com.charlancodes.fashapi.model.BaseIdClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "likes")
public class Like extends BaseIdClass {
    public Like() {

    }
    @Enumerated(EnumType.STRING)
    private Reaction reaction;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private BlogPost blogPost;


}
