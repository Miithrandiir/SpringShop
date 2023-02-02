package fr.ulco.springshop.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Table(name = "Users")
@Entity
@Setter
@Getter
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "created_at",  updatable = false, columnDefinition = "timestamp default current_timestamp", insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at",  columnDefinition = "timestamp default current_timestamp", insertable = false)
    private LocalDateTime updatedAt;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true", insertable = false)
    private boolean enabled;

    public UserEntity(){
        enabled = true;
    }
}
