package com.github.kotelkov.pms.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "roles")
@Entity
@Getter
@Setter
@ToString(exclude = "userAuths")
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<UserAuth> userAuths;
}
