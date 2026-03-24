package com.tienhuu.buoi07test.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, columnDefinition = "NVARCHAR(255)")
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Doctor> doctors;
}
