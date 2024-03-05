package com.cgi.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cgi.model.*;
public interface UserRepository extends JpaRepository<UserApp, Long>{
	UserApp findByMailAndPassword(String mail, String password);

}
