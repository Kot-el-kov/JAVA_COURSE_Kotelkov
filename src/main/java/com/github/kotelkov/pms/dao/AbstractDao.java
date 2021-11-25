package com.github.kotelkov.pms.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
    public void save(Entity entity) {
        entityManager.persist(entity);
    }

    @Override
    public Entity getById(Id id) {
        return entityManager.find(entityClass,id);
    }

    @Override
    public void update(Entity entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(entityClass,id));
    }

    @Override
    public List<Entity> getAll() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Entity> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        final Root<Entity> rootEntry = criteriaQuery.from(entityClass);
        CriteriaQuery<Entity> query = criteriaQuery.select(rootEntry);
        TypedQuery<Entity> allQuery = entityManager.createQuery(query);
        return allQuery.getResultList();
    }
}
