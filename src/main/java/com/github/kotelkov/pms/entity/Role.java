package com.github.kotelkov.pms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "roles")
@Entity
@Data
@NoArgsConstructor
public class Role {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "role")
    private List<UserAuth> userAuths;
}
