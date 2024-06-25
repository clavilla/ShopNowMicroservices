package com.shopnow.user.controller;

import com.shopnow.user.model.dto.ErrorResponseDto;
import com.shopnow.user.model.dto.UserRequestDto;
import com.shopnow.user.model.dto.UserResponseDto;
import com.shopnow.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    /*@ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request, please follow the API documentation for the proper structure",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })*/
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userRequestDto){
        return new ResponseEntity<>(userService.createUser(userRequestDto), HttpStatus.CREATED);
    }
}
