package com.example.librarycatalog.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    @NotEmpty(message = "Enter the title of the book")
    private String title;

    @NotEmpty(message = "Upload the book cover")
    @Column(name = "cover_book")
    private String coverUrlBook;

    @Length(max = 1500, message = "The book summary should be shorter, less than 1500 characters")
    private String bookSummary;

    @ManyToMany
    @JoinTable(
            name = "books_keywords",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id")
    )
    private List<Keyword> keywords;

}
