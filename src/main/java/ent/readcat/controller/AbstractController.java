package ent.readcat.controller;

import ent.readcat.service.user.BaseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractController<S extends BaseService> {
    protected final S service;
}
