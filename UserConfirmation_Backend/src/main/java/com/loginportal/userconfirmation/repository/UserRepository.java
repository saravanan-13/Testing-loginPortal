package com.loginportal.userconfirmation.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.loginportal.userconfirmation.model.Register;

@Repository
public interface UserRepository extends CrudRepository<Register, Long>{
	@Query("select u from Register u  where u.emailID = ?1")
    Register findByEmailId(String id);

}
