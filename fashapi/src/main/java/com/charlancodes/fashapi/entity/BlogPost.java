package com.charlancodes.fashapi.entity;

import com.charlancodes.fashapi.enums.Category;
import com.charlancodes.fashapi.model.BaseIdClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table

public class BlogPost extends BaseIdClass {
    private String title;
    private String description;
    private String price;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Admin admin;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "blogPost")
    private List<Comment> comment;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "blogPost")
    private List<Like> likes;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "blogPost")
    private BlogPostImage blogPostImage;

}

