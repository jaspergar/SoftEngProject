package io.jg_intelligence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.jg_intelligence.entity.Backlog;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long>{

 Backlog findBySubjectNamePrefix(String prefix);
 Backlog findBySubjectId(Long id);
	
}
