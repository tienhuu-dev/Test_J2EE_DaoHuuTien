package com.tienhuu.buoi07test.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(nullable = false, unique = true, columnDefinition = "NVARCHAR(255)")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Patient> patients;
}
