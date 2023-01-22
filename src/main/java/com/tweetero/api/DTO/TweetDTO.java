package com.tweetero.api.DTO;

import com.tweetero.api.models.User;

import jakarta.validation.constraints.NotBlank;

public record TweetDTO(

        @NotBlank User user,

        @NotBlank String text) {

}
