package ru.itupolev.hackaton.entity;

import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "user_table")
@Setter
@Getter
public class User {
    @Id
    private UUID id = UuidCreator.getTimeOrderedEpoch();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean teamLead;

    @Column(nullable = false)
    private boolean searchingCommand;

    @Column(nullable = false)
    private String organization;

    private String teamName;

    @Column(nullable = false)
    private String telegramName;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

}

