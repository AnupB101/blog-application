package com.anup.blog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="post" ,uniqueConstraints = {@UniqueConstraint(columnNames ={"title"})})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "title")
    @NotNull
    private String title;
    @Column(name ="description")
    @NotNull
    private String description;

    @NotNull
    @Column(name = "content")
    private String Content;


}
