package com.egorlins.springboot.repository;

import com.egorlins.springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
