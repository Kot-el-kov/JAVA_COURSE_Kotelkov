package com.github.kotelkov.pms.entity;

import com.github.kotelkov.pms.dto.ProductDto;
import lombok.*;
import org.hibernate.mapping.FetchProfile;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stores")
@Getter
@Setter
@ToString(exclude = "products")
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(
        name="with-products",
        attributeNodes = @NamedAttributeNode("products")
)
public class Store {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "stores_products",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

}
