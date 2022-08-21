package ent.readon.service.user;

import ent.readon.mapper.MyMapper;
import ent.readon.repo.BaseRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractService<R extends BaseRepo, M extends MyMapper> {

    protected final R repo;
    protected final M mapper;
}
