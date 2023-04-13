package com.example.webSpring.api.security.Jwt;

import com.example.webSpring.api.model.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.file.attribute.UserPrincipal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class TokenProvider {
    // Key bí mật để ký và giải mã JWT
    private final String SECRET_KEY = "mySecretKey";

    // Thời gian hết hạn của JWT (30 phút)
    private final long EXPIRATION_TIME = 1800000;

    public String generateToken(Authentication authentication) {
        // Lấy thông tin người dùng xác thực từ đối tượng Authentication
        User userPrincipal = (User) authentication.getPrincipal();

        // Tạo dữ liệu Payload cho JWT
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userPrincipal.getId());
        claims.put("username", userPrincipal.getPhoneNumber());
        // Thêm các thông tin khác của người dùng vào Payload

        // Tạo JWT
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userPrincipal.getPhoneNumber())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
