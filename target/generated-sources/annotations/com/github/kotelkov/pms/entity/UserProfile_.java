package com.github.kotelkov.pms.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserProfile.class)
public abstract class UserProfile_ {

	public static volatile SingularAttribute<UserProfile, String> surname;
	public static volatile SingularAttribute<UserProfile, UserAuth> userAuth;
	public static volatile SingularAttribute<UserProfile, String> name;
	public static volatile SingularAttribute<UserProfile, Long> id;
	public static volatile SingularAttribute<UserProfile, String> email;

	public static final String SURNAME = "surname";
	public static final String USER_AUTH = "userAuth";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String EMAIL = "email";

}

