package com.gatcha.authentication.controller;

import com.gatcha.authentication.domain.UserDto;
import com.gatcha.authentication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/signUp")
  public ResponseEntity<String> signUpUser(@RequestBody @Valid UserDto userDto) {
    return ResponseEntity.ok(userService.signUp(userDto));
  }

  @PostMapping("/login")
  public ResponseEntity<Boolean> login(@RequestBody String token) {
    return ResponseEntity.ok(userService.login(token));
  }
}
