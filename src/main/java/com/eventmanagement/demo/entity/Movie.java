package com.eventmanagement.demo.entity;

import com.eventmanagement.demo.enums.Genre;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private String posterUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;
}
