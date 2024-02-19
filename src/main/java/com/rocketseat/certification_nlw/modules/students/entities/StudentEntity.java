package com.rocketseat.certification_nlw.modules.students.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique =  true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "studentEntity")
    private List<CertificationStudentEntity> certificationStudentEntities;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
