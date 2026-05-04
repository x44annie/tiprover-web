package com.note.tiprover.entity;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.Email;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false, columnDefinition = "uuid DEFAULT uuidv7()")
    private UUID uid = Generators.timeBasedEpochGenerator().generate();

    @Column(unique = true, nullable = false)
    private String usr;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
}
