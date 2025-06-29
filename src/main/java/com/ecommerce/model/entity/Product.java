package com.ecommerce.model.entity;

import com.ecommerce.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity<Long> {

    private String name;
    private String description;
    private String image;
    private Double price;
    private int stack;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
