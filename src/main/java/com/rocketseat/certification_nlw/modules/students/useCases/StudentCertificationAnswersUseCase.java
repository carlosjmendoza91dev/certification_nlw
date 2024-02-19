package com.rocketseat.certification_nlw.modules.students.useCases;

import com.rocketseat.certification_nlw.modules.questions.entities.QuestionEntity;
import com.rocketseat.certification_nlw.modules.questions.repositories.QuestionsRepository;
import com.rocketseat.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import com.rocketseat.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.rocketseat.certification_nlw.modules.students.entities.StudentEntity;
import com.rocketseat.certification_nlw.modules.students.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private StudentRepository studentRepository;

    public StudentCertificationAnswerDTO execute(StudentCertificationAnswerDTO dto) {

        List<QuestionEntity> questionsEntity = questionsRepository.findByTechnology(dto.getTechnology());

        dto.getQuestionsAnswers()
                .stream()
                .forEach(questionAnswer -> {
                    var question = questionsEntity.stream().filter(q -> q.getId().equals(questionAnswer.getQuestionId())).findFirst().get();

                    var findCorrectAlternative = question.getAltenatives().stream().filter(alternative -> alternative.isCorrect()).findFirst().get();

                    if(findCorrectAlternative.getId().equals(questionAnswer.getAlternativeId())){
                        questionAnswer.setCorrect(true);
                    } else {
                        questionAnswer.setCorrect(false);
                    }
                });

        var student = studentRepository.findByEmail(dto.getEmail());
        UUID studentId;
        if(student.isEmpty()){
            var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
            studentCreated = studentRepository.save(studentCreated);
            studentId = studentCreated.getId();
        } else {
            studentId = student.get().getId();
        }

        CertificationStudentEntity certificationStudentEntity =
                CertificationStudentEntity.builder()
                        .technology(dto.getTechnology())
                        .studentId(studentId)
                        .build();

        return dto;
    }
}
