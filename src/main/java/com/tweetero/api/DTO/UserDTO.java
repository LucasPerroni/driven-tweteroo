package com.tweetero.api.DTO;

import jakarta.validation.constraints.Pattern;

public record UserDTO(

        @Pattern(regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]") String avatar) {

}
