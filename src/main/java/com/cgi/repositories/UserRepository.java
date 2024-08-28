package com.cgi.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cgi.model.*;
public interface UserRepository extends JpaRepository<UserApp, Long>{
	UserApp findByMailAndPassword(String mail, String password);
	List<UserApp> findByRole(Role role);

}

