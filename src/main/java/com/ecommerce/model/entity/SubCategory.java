package com.ecommerce.model.entity;

import com.ecommerce.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
//@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subcategories")
public class SubCategory extends BaseEntity<Long> {

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "category_id", nullable = false)
//    @JsonIgnore
//    @JsonBackReference
    private Category category;

    public SubCategory(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
