package com.rocketseat.certification_nlw.modules.students.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "answers_certifications_students")
public class AnswersCertificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "certification_id")
    private UUID certificationId;

    @ManyToOne()
    @JoinColumn(name = "certification_id", insertable = false, updatable = false)
    private CertificationStudentEntity certificationStudentEntity;

    @Column(name = "student_id")
    private UUID studentId;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private StudentEntity studentEntity;

    @Column(name = "question_id")
    private UUID questionId;

    @Column(name = "answer_id")
    private UUID answerId;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
