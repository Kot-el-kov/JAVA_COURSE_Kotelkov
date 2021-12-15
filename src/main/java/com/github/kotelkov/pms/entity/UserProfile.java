package com.github.kotelkov.pms.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "users_profiles")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "userProfile")
    private UserAuth userAuth;
}
