package io.jg_intelligence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.jg_intelligence.entity.Schedule;
import io.jg_intelligence.repository.Custom.ScheduleRepositoryCustom;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>{

	List<Schedule> findAllByDayAndRoomNumber(String day,String roomNumber);
	Schedule findOneByEdpCode(String edpCode);
	Schedule findOneById(Long id);
}
