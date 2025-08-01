package com.fitple.fitple.base.user.domain;


import jakarta.persistence.*;
import lombok.*;
import com.fitple.fitple.base.user.dto.UserDTO;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String auth;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 50)
    private String nickname;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // UserDTO로부터 값을 받아서 User 객체 생성
    public User(UserDTO dto) {
        this.email = dto.getEmail();
        this.password = dto.getPassword();  // 비밀번호는 암호화 없이 저장하기 전에 설정할 수 있습니다
        this.nickname = dto.getNickname();
        this.createdAt = LocalDateTime.now();  // 기본 생성일자
        this.updatedAt = LocalDateTime.now();  // 기본 수정일자
    }
    public void changePassword(String password) {
        this.password = password;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }
}
