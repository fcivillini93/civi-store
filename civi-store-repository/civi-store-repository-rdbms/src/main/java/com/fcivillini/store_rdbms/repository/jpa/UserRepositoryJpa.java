package com.fcivillini.store_rdbms.repository.jpa;

import com.fcivillini.store_rdbms.entity.UserRdbms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserRdbms, Long> {


}