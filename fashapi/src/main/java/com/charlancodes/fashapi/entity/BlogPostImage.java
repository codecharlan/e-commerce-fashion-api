package com.charlancodes.fashapi.entity;

import com.charlancodes.fashapi.model.BaseIdClass;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table
public class BlogPostImage extends BaseIdClass {
    private String name;

    private String type;
    @Lob
    @Column(name = "image", length = 1000)
    private byte[] image;

    @OneToOne(fetch = FetchType.EAGER)
    private BlogPost blogPost;

    public BlogPostImage(String s) {
    }
}
