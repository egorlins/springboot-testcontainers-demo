package com.egorlins.springboot.repository;

import com.egorlins.springboot.AbstractContainerBaseTest;
import com.egorlins.springboot.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest extends AbstractContainerBaseTest {

    @Autowired
    private StudentRepository studentRepository;

    //JUnit for save student operation
    @Test
    public void giverStudentObject_whenSave_thenReturnSavedStudent(){
        //given - setup or precondition
        Student student = Student.builder().firstName("John").lastName("Smith").email("john@gmail.com").build();

        //when - action or actual testing
        Student savedStudent = studentRepository.save(student);

        //then - verify output
        Assertions.assertNotNull(savedStudent);
    }

    @Test
    public void giverStudentObject_whenFindById_thenReturnSavedStudent(){
        //given - setup or precondition
        Student student = Student.builder().firstName("John").lastName("Smith").email("john@gmail.com").build();
        Student savedStudent = studentRepository.save(student);

        //when - action or actual testing
        Assertions.assertNotNull(savedStudent);
        Student foundStudent = studentRepository.findById(savedStudent.getId()).get();

        //then - verify output
        Assertions.assertNotNull(foundStudent);
    }
}