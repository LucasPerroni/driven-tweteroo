package com.tweetero.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweetero.api.models.UserTable;

public interface UserRepository extends JpaRepository<UserTable, Long> {

    UserTable findByUsername(String username);

}
