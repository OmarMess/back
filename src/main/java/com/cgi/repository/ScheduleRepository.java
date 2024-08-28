package com.cgi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cgi.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
	List<Schedule> findByEvaluatorId(Long evaluatorId);
}

