package com.github.kotelkov.pms.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "users_auth")
@Entity
@Getter
@Setter
@Builder
@ToString(exclude = "role")
@AllArgsConstructor
@NoArgsConstructor
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userAuth-id-sequence")
    @SequenceGenerator(name = "userAuth-id-sequence", sequenceName = "users_auth_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToOne(fetch = FetchType.LAZY,orphanRemoval = true)
    @JoinColumn(name = "id")
    private UserProfile userProfile;

    public UserAuth(String login,String password,Role role){
        this.login=login;
        this.password=password;
        this.role=role;
    }
}
