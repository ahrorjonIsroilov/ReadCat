package ent.readon.controller;

import ent.readon.service.user.BaseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractController<S extends BaseService> {
    protected final S service;
}
