package io.jg_intelligence.repository.Custom.Impl;




import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.context.Theme;


import io.jg_intelligence.entity.Subject;
import io.jg_intelligence.repository.Custom.SubjectRepositoryCustom;

@Repository
@Transactional(readOnly= true)
public class SubjectRepositoryCustomImpl implements SubjectRepositoryCustom{
@PersistenceContext 
EntityManager entityManager;

@Override
public List<Subject> findAllSubjectAndSchedule() {
	// TODO Auto-generated method stub
	
	
	String sqlQuery =  "SELECT sub FROM Subject sub RIGHT JOIN Schedule sched ON"
			+ "sub.subjectNamePrefix= sched.subjectPrefix GROUP BY sub.id ORDER BY sub.id DESC";
	Query query = entityManager.createNativeQuery(sqlQuery, Subject.class);
//	 query.setParameter(1, subjectPrefix + "%");
	return query.getResultList();
}



}	

