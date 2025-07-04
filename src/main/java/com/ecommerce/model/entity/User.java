package com.ecommerce.model.entity;

import com.ecommerce.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@NamedEntityGraph(
        name = "loadUserRoles",
        attributeNodes = @NamedAttributeNode("roles"))
public class User extends BaseEntity<Long> {

    private String name;
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    private String phoneNumber;
    private String address;
    @Column(nullable = false)
    private boolean isActive = true;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

}
