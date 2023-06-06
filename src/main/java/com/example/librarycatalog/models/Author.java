package com.example.librarycatalog.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Book> book;

    @Builder
    public Author(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
