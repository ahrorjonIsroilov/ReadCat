package ent.readcat.service;

import ent.readcat.mapper.MyMapper;
import ent.readcat.repo.BaseRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractService<R extends BaseRepo, M extends MyMapper> {

    protected final R repo;
    protected final M mapper;
}
