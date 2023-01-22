package com.tweetero.api.DTO;

import com.tweetero.api.models.UserTable;

import jakarta.validation.constraints.NotBlank;

public record TweetDTO(

        @NotBlank UserTable user,

        @NotBlank String text) {

}
