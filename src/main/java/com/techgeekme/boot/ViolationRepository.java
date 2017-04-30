package com.techgeekme.boot;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ViolationRepository extends JpaRepository<Violation, Long>, JpaSpecificationExecutor<Violation> {

	@Query("select count(v) from Violation v group by date(timeOfViolation)")
	public List<Long> countGrounpByViolationTime();
	
//	@Query("select count(v.id) date(v.timeOfViolation) from Violation v group by date(v.timeOfViolation)")
//	public Map<Long, String> countByViolationTime();
	
	 @Query("select to_char(v.timeOfViolation, 'DD/MM/YYYY'), count(v) from Violation v group by to_char(v.timeOfViolation, 'DD/MM/YYYY')")
	 public List<Object[]> countByViolationTime();
	 
	 
//	@Query(value = "select count(v), date(timeOfViolation) from Violation v group by date(timeOfViolation)", 
//            countQuery = "select count(1) from (select count(1) from Violation v group by date(timeOfViolation)) z")
//     public List<Object[]> findCountPerDay();
}
