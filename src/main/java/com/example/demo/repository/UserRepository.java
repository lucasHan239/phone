package com.example.demo.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findByUsernameAndPassword(String Username, String Password) ;

	@Modifying
    @Query(value = "insert into users (username, password) VALUES (?1,?2)", nativeQuery = true)
    @Transactional
    void insertUser(String username, String passwords);
}
