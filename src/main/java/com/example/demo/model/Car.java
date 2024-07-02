package com.example.demo.model;

import com.example.demo.utils.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "car")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Transient
    private String color;

    @Transient
    private List<Role> role;

    private String activationCode;

    public Car(String name) {
        this.name = name;
    }
}
