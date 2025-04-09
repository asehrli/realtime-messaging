package com.example.realtimemessaging.entity;

import com.example.realtimemessaging.enums.PermissionEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role extends AbsBaseEntity {
    @Column(nullable = false, unique = true)
    String name;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    List<PermissionEnum> permissions;
}
