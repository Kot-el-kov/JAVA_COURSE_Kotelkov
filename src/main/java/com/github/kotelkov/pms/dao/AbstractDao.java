package com.github.kotelkov.pms.dao;

import org.springframework.data.domain.Pageable;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AbstractDao<Entity, Id> implements GenericDao<Entity, Id>{

    @PersistenceContext
    protected EntityManager entityManager;
    protected Class<Entity> entityClass;

    public AbstractDao(Class<Entity> entityClass){
        this.entityClass=entityClass;
    }

    @Override
    public Entity save(Entity entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Entity getById(Id id) {
        return entityManager.find(entityClass,id);
    }

    @Override
    public Entity update(Entity entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(entityClass,id));
    }

    @Override
    public List<Entity> getAll(Pageable pageable) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Entity> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        final Root<Entity> rootEntry = criteriaQuery.from(entityClass);
        CriteriaQuery<Entity> query = criteriaQuery.select(rootEntry);
        query.orderBy(criteriaBuilder.asc(rootEntry.get(pageable.getSort().toList().get(0).getProperty())));
        TypedQuery<Entity> allQuery = entityManager.createQuery(query);
        allQuery.setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize());
        return allQuery.getResultList();
    }
}
