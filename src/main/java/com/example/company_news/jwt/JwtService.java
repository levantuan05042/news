package com.example.company_news.jwt;

import com.example.company_news.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    private SecretKey secretKey; //Biến chứa khóa bí mật dùng để ký và xác thực token JWT

    //Biến secretKey sẵn sàng dùng cho việc ký và xác minh JWT sau này.
    @PostConstruct // hàm này sẽ chạy sau khi Spring inject xong tất cả biến
    public void init() {
        secretKey = new SecretKeySpec(jwtSecret.getBytes(),
                SignatureAlgorithm.HS256.getJcaName()); //tạo khóa đối xứng (HS256) từ chuỗi bí mật jwtSecret
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail()) // định danh
                .claim("id", user.getId())
                .claim("role", user.getRole())
                .claim("fullName", user.getFullname())
                .claim("password", user.getPassword())
                .claim("image", user.getImage())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(secretKey)
                .compact();
    }
}
