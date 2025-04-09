package com.example.simplesecuredmessaging.entity;


import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@Getter
@Setter
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class AbsOwnBaseEntity extends AbsBaseEntity {
    @CreatedBy
    @ManyToOne(optional = false)
    User createdBy;

    @LastModifiedBy
    @ManyToOne
    User updatedBy;
}
