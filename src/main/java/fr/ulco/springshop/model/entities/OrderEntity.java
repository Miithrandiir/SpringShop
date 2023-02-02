package fr.ulco.springshop.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Table(name = "user_orders")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_at", updatable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "order")
    private Collection<OrderItemEntity> items;
}
