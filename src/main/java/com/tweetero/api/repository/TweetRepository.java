package com.tweetero.api.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.tweetero.api.models.Tweet;

public interface TweetRepository extends JpaRepositoryImplementation<Tweet, Long> {

}
