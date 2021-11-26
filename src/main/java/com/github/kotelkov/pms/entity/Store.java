package com.github.kotelkov.pms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "stores")
@Entity
@Data
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
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "stores")
    private List<Product> products;
}
