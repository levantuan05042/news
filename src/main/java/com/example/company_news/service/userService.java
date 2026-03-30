package com.example.company_news.service;

import com.example.company_news.handler.NotFoundException;
import com.example.company_news.jwt.JwtService;
import com.example.company_news.model.User;
import com.example.company_news.model.dto.LoginRequest;
import com.example.company_news.model.dto.RegisterRequest;
import com.example.company_news.model.dto.UsersRequest;
import com.example.company_news.model.dto.UsersResponse;
import com.example.company_news.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class userService extends BaseService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(RegisterRequest request) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email đã được dùng rồi, đồ ngốc ạ");
        }
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Integer.valueOf("0"));
        user.setFullname(request.getFullName());
        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Email không đúng"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Password sai");
        }
        return jwtService.generateToken(user);
    }

    public UsersResponse detail(String id) throws NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy"));

        return dto(user, UsersResponse.class);
    }

    public UsersResponse update(String id, UsersRequest request, MultipartFile file) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy"));
        if (request.getCurrentPassword() == null || !passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Mật khẩu hiện tại không đúng");
        }
        if (file != null && !file.isEmpty()) {
            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String fileName = "avatar_" + id + "." + extension;

            Path path = Paths.get("D:/KenhVanHoaSo/uploads/" + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            user.setImage(fileName);
        }
        if (request.getFullName() != null) user.setFullname(request.getFullName());
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        userRepository.save(user);
        return dto(user, UsersResponse.class);
    }
}
