package com.example.realtimemessaging.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
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
@Builder
public class User extends AbsBaseEntity implements UserDetails {
    @Column(nullable = false)
    String name;

    @Column(nullable = false, unique = true)
    String email;

    String picture;

    String password = UUID.randomUUID().toString();

    @ManyToMany(fetch = FetchType.EAGER)
    List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(Role::getPermissions)
                .flatMap(Collection::stream)
                .toList();
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
