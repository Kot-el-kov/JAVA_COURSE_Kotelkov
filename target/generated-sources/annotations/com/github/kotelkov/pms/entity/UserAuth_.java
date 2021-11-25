package com.github.kotelkov.pms.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserAuth.class)
public abstract class UserAuth_ {

	public static volatile SingularAttribute<UserAuth, String> password;
	public static volatile SingularAttribute<UserAuth, Role> role;
	public static volatile SingularAttribute<UserAuth, Long> id;
	public static volatile SingularAttribute<UserAuth, String> login;
	public static volatile SingularAttribute<UserAuth, UserProfile> userProfile;

	public static final String PASSWORD = "password";
	public static final String ROLE = "role";
	public static final String ID = "id";
	public static final String LOGIN = "login";
	public static final String USER_PROFILE = "userProfile";

}

