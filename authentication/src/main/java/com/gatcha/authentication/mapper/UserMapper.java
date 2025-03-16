package com.gatcha.authentication.mapper;

import com.gatcha.authentication.domain.TokenInformation;
import com.gatcha.authentication.domain.User;
import com.gatcha.authentication.domain.UserDto;
import java.time.LocalDateTime;

public class UserMapper {

  public static User mapNewUser(UserDto userDto) {

    TokenInformation tokenInformation = new TokenInformation();
    tokenInformation.setCreatedAt(LocalDateTime.now());
    tokenInformation.setExpiresAt(LocalDateTime.now().plusHours(1));

    return new User(userDto.getUserId(), userDto.getPassword(), tokenInformation);
  }
}
