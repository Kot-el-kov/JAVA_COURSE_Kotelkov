package com.github.kotelkov.pms.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Store.class)
public abstract class Store_ {

	public static volatile SingularAttribute<Store, String> address;
	public static volatile SingularAttribute<Store, String> name;
	public static volatile SingularAttribute<Store, Long> id;
	public static volatile ListAttribute<Store, Product> products;

	public static final String ADDRESS = "address";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String PRODUCTS = "products";

}

