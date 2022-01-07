package com.github.kotelkov.pms.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface GenericDao<Entity, Id> {
    Entity save(Entity entity);
    Entity getById(Id id);
    Entity update(Entity entity);
    void delete(Long id);
    List<Entity> getAll(Pageable pageable);
}
