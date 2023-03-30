package fr.ulco.springshop.model.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductBO {

    private int id;
    private String name;
    private float price;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String description;
    private String thumbnail;
    private Collection<CategoryBO> categories;
    private boolean isHighlighted;

    public ProductBO(String name, float price, int quantity, String description, String thumbnail, Collection<CategoryBO> categories, boolean isHighlighted) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.thumbnail = thumbnail;
        this.categories = categories;
        this.isHighlighted = isHighlighted;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Collection<CategoryBO> getCategories() {
        return categories;
    }

    public boolean isHighlighted() {
        return isHighlighted;
    }
}
