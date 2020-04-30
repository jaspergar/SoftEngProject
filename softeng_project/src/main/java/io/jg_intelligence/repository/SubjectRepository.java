package io.jg_intelligence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.jg_intelligence.entity.Subject;
import io.jg_intelligence.repository.Custom.SubjectRepositoryCustom;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>, SubjectRepositoryCustom{
Subject findOneBySubjectNamePrefix(String subjectPrefix);

List<Subject> findAllByOrderByIdDesc();

//@Query("select new SubjectAndScheduleJoin(sub.id, sub.subjectNamePrefix, sub.subjectFullname, sub.subjectType, sub.collegeStanding,sched.id,sched.roomNumber) from Subject sub, Schedule sched where sub.subjectNamePrefix = sched.subjectPrefix")
@Query("SELECT sub,sched.roomNumber FROM Subject sub RIGHT JOIN Schedule sched ON sub.subjectNamePrefix = sched.subjectPrefix GROUP BY sub.id ORDER BY sub.id DESC")
List<Subject> findSubjectAndSchedule();

List<Subject> findAllSubjectAndSchedule();

}	
