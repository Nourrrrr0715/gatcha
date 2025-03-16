package com.gatcha.invocation.controller;

import com.gatcha.invocation.model.Invocation;
import com.gatcha.invocation.service.InvocationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "Authorization")
@RequestMapping("/invocation")
public class InvocationController {

  private final InvocationService invocationService;

  public InvocationController(InvocationService invocationService) {
    this.invocationService = invocationService;
  }

  // Endpoint pour invoquer un monstre
  @PostMapping("/invoke")
  public ResponseEntity<?> invokeMonster(
      @RequestParam String playerId, @RequestHeader("Authorization") String authorizationHeader) {
    try {
      Invocation invocation = invocationService.processInvocation(playerId, authorizationHeader);
      return ResponseEntity.ok(invocation);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Failed to invoke monster: " + e.getMessage());
    }
  }
}
