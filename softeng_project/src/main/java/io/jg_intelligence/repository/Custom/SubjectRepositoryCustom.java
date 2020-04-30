package io.jg_intelligence.repository.Custom;

import java.util.List;


import io.jg_intelligence.entity.Subject;
public interface SubjectRepositoryCustom {
	List<Subject> findAllSubjectAndSchedule();
}
