package io.jg_intelligence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.jg_intelligence.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
 Teacher findOneByIdNumber( String idNumber);
 List<Teacher> findAll();
}
