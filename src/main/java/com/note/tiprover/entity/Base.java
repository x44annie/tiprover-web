package com.note.tiprover.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@SQLDelete(sql = "UPDATE users SET deletedAt = NOW() WHERE id = ?")
@FilterDef(name = "deletedFilter", defaultCondition = "deletedAt IS NULL")
@Filter(name = "deletedFilter")
public abstract class Base {
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleteAt")
    private LocalDateTime deletedAt;
}