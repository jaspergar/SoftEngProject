package io.jg_intelligence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.jg_intelligence.entity.Admin;



@Repository
public interface AdminRepository extends CrudRepository<Admin, Long>{
  Admin findByUsername(String username);
  Admin getById(Long id);
}
