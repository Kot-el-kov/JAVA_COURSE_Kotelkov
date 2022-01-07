package com.github.kotelkov.pms.dao.impl;

import com.github.kotelkov.pms.dao.AbstractDao;
import com.github.kotelkov.pms.dao.RoleRepository;
import com.github.kotelkov.pms.entity.Role;
import com.github.kotelkov.pms.entity.Role_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class RoleRepositoryImpl extends AbstractDao<Role, Long> implements RoleRepository {
    public RoleRepositoryImpl() {
        super(Role.class);
    }

    @Override
    public Role getRoleByName(String name){
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Role> query = criteriaBuilder.createQuery(Role.class);
        final Root<Role> from = query.from(Role.class);
        query.where(criteriaBuilder.equal(from.get(Role_.name), name));
        return entityManager.createQuery(query).getSingleResult();
    }

}
