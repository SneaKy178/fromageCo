package com.mfelton.Controller;

import com.mfelton.Service.UserService;
import com.mfelton.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login/{email}/{password}") //tester
    public ResponseEntity<User> login(
            @PathVariable("email") String email, @PathVariable("password") String password) {
        return userService
                .login(email, password)
                .map(user1 -> ResponseEntity.status(HttpStatus.OK).body(user1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> findUserByEmail(@PathVariable("email") String email) { //tester
        return userService
                .findUserByCourriel(email)
                .map(user1 -> ResponseEntity.status(HttpStatus.OK).body(user1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }
}
