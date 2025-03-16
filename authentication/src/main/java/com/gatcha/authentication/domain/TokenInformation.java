package com.gatcha.authentication.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenInformation {
  private LocalDateTime createdAt;
  private LocalDateTime expiresAt;
}
