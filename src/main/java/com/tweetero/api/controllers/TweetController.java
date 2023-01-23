package com.tweetero.api.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetero.api.DTO.TweetDTO;
import com.tweetero.api.models.Tweet;
import com.tweetero.api.models.UserTable;
import com.tweetero.api.repository.TweetRepository;
import com.tweetero.api.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> createTweet(@RequestBody @Valid TweetDTO req) {
        UserTable user = userRepository.findByUsername(req.username());

        if (user == null) {
            return ResponseEntity.status(404).body("Username not found");
        }

        Tweet tweet = new Tweet(req, user);

        tweetRepository.save(tweet);
        return ResponseEntity.status(200).build();
    }

    @GetMapping
    public List<Map<Object, Object>> getTweets(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        Page<Tweet> list = tweetRepository.findAll(pageable);
        List<Map<Object, Object>> formatedList = new ArrayList<>();

        list.forEach(i -> {
            Map<Object, Object> data = new HashMap<>();
            data.put("tweet", i.getTweet());
            data.put("username", i.getUser().getUsername());
            data.put("avatar", i.getUser().getAvatar());

            formatedList.add(data);
        });

        return formatedList;
    }

    @GetMapping("/{username}")
    public List<Map<Object, Object>> getTweetsByUsername(@PathVariable String username) {
        List<Tweet> list = tweetRepository.findAll();
        List<Map<Object, Object>> formatedList = new ArrayList<>();

        list.forEach(i -> {
            if (i.getUser().getUsername().equals(username)) {
                Map<Object, Object> data = new HashMap<>();
                data.put("tweet", i.getTweet());
                data.put("username", i.getUser().getUsername());
                data.put("avatar", i.getUser().getAvatar());

                formatedList.add(data);
            }
        });

        return formatedList;
    }

}
