package com.example.simplesecuredmessaging.entity;

import com.example.simplesecuredmessaging.enums.PermissionEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User extends AbsBaseEntity implements UserDetails {
    @Column(nullable = false)
    String name;

    @Column(nullable = false, unique = true)
    String email;

    String picture;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<PermissionEnum> authorities;

    @Transient
    private final String password = UUID.randomUUID().toString();

    @Override
    public String getUsername() {
        return this.email;
    }
}
