package fr.ulco.springshop.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="products_images")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class ProductImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image", nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private ProductEntity product;
}
