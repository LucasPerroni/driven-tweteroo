package com.tweetero.api.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.tweetero.api.models.User;

public interface UserRepository extends JpaRepositoryImplementation<User, Long> {

}
