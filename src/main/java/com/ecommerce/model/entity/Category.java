package com.ecommerce.model.entity;

import com.ecommerce.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
@NamedEntityGraph(
        name = "loadSubCategories",
        attributeNodes = @NamedAttributeNode("subCategories"))
public class Category extends BaseEntity<Long> {

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @Formula("(select count(sc.id) from subcategories sc where sc.category_id = id)")
    private Integer subCategoryCount;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//    @JsonManagedReference
    private List<SubCategory> subCategories = new ArrayList<>();

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                '}';
    }

}
