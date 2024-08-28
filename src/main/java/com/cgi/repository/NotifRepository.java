package com.cgi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.model.Notif;
import com.cgi.model.UserApp;

@Repository 
public interface NotifRepository extends JpaRepository<Notif, Long> {
    List<Notif> findByEvaluator(UserApp evaluator);
}
