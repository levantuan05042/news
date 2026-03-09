package com.example.company_news.controller;

import com.example.company_news.handler.NotFoundException;
import com.example.company_news.model.dto.LoginRequest;
import com.example.company_news.model.dto.RegisterRequest;
import com.example.company_news.model.dto.UsersRequest;
import com.example.company_news.model.dto.UsersResponse;
import com.example.company_news.repository.UserRepository;
import com.example.company_news.model.User;
import com.example.company_news.service.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final userService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest request) {
        String token = userService.register(request);
        return ResponseEntity.ok().body("Đăng ký thành công. Token là: " + token);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        String token = userService.login(request);

        return ResponseEntity.ok().body(Map.of(
                "message", "Đăng nhập thành công",
                "token", token
        ));
    }

    @PostMapping("/detail/{id}")
    public UsersResponse viewDetail(@PathVariable("id") String id) throws NotFoundException {
        return userService.detail(id);
    }

    @PostMapping("/update/{id}")
    public UsersResponse update(@PathVariable String id, @RequestBody @Valid UsersRequest request) throws Exception {
        return userService.update(id, request);
    }
}
