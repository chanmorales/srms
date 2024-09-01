package com.portfolio.mutex.srms.controller;

import static com.portfolio.mutex.srms.common.UriConstants.CURRENT_USER_URI;
import static com.portfolio.mutex.srms.common.UriConstants.USERS_BASE_URI;

import com.portfolio.mutex.srms.dto.JwtDto;
import com.portfolio.mutex.srms.dto.UserDto;
import com.portfolio.mutex.srms.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(USERS_BASE_URI)
@Tag(name = "Users")
public class UserController {

  private final UserService userService;

  @Operation(summary = "Current user",
      description = "Retrieves the details of the  currently logged in user")
  @ApiResponse(responseCode = "200", description = "Request is successful.",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = JwtDto.class),
          examples = @ExampleObject(value = """
              {
                "username": "jdoe",
                "email": "john.doe@example.com",
                "displayName": "Doe, John F.",
                "active": true,
                "admin": false,
                "systemAdmin": false
              }
              """)))
  @GetMapping(value = CURRENT_USER_URI, produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<UserDto> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
    return ResponseEntity.ok(userService.getCurrentUser(userDetails.getUsername()));
  }
}
