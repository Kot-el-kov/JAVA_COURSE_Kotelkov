package com.github.kotelkov.pms.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "users_profiles")
@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "userProfile")
    private UserAuth userAuth;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "wishlists",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> wishlist;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "histories",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> history;
}
