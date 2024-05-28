package com.example.demo.domain;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pid", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String log;

    @Column(nullable = false)
    private LocalDate date;
}

