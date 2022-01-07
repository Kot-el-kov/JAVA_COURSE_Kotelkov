package com.github.kotelkov.pms.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "roles")
@Entity
@Getter
@Setter
@ToString(exclude = "userAuths")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role-id-sequence")
    @SequenceGenerator(name = "role-id-sequence", sequenceName = "roles_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<UserAuth> userAuths;

    public Role(Long id,String name){
        this.id=id;
        this.name=name;
    }
}
