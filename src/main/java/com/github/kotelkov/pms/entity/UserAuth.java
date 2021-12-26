package com.github.kotelkov.pms.entity;

import lombok.*;

import javax.management.ConstructorParameters;
import javax.persistence.*;

@Table(name = "users_auth")
@Entity
@Getter
@Setter
@ToString(exclude = "role")
@AllArgsConstructor
@NoArgsConstructor
public class UserAuth {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private UserProfile userProfile;

    public UserAuth(String login,String password,Role role){
        this.login=login;
        this.password=password;
        this.role=role;
    }
}
