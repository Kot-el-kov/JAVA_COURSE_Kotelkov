package com.github.kotelkov.pms.dao;

import java.util.List;

public interface GenericDao<Entity, Id> {
    void save(Entity entity);
    Entity getById(Id id);
    void update(Entity entity);
    void delete(Long id);
    List<Entity> getAll();
}
