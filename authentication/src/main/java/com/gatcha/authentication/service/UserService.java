package com.gatcha.authentication.service;

import com.gatcha.authentication.domain.User;
import com.gatcha.authentication.domain.UserDto;
import com.gatcha.authentication.mapper.UserMapper;
import com.gatcha.authentication.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  public static final String FAILED_TO_CREATE_TOKEN = "Failed to create token";
  public static final String FAILED_TO_SAVE_USER = "Failed to save user";
  public static final String INVALID_TOKEN = "Invalid token";
  public static final String INVALID_USER = "Invalid user";
  private final TokenService tokenService;
  private final UserRepository userRepository;

  public UserService(TokenService tokenService, UserRepository userRepository) {
    this.tokenService = tokenService;
    this.userRepository = userRepository;
  }

  public String signUp(UserDto userDto) {
    User user = UserMapper.mapNewUser(userDto);
    return Optional.of(userRepository.save(user))
        .map(
            savedUser -> {
              try {
                return tokenService.createToken(
                    savedUser.getUserId(), savedUser.getTokenInformation().getCreatedAt());
              } catch (Exception e) {
                throw new RuntimeException(FAILED_TO_CREATE_TOKEN, e);
              }
            })
        .orElseThrow(() -> new RuntimeException(FAILED_TO_SAVE_USER));
  }

  public boolean login(String token) {
    try {
      String data = tokenService.validateTokenAndGetData(token);
      String userId = data.split("-")[0];
      return userRepository
          .findByUserId(userId)
          .map(
              user -> {
                user.getTokenInformation().setExpiresAt(LocalDateTime.now().plusHours(1));
                userRepository.save(user);
                return true;
              })
          .orElseThrow(() -> new RuntimeException(INVALID_USER));
    } catch (Exception e) {
      throw new RuntimeException(INVALID_TOKEN, e);
    }
  }
}
