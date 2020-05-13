package pl.lodz.p.it.tks.userservice.services.impl;

import pl.lodz.p.it.tks.userservice.adapters.aggregates.UserCrudAdapter;
import pl.lodz.p.it.tks.userservice.services.UserCrudService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Dependent
public class UserCrudServiceImpl implements UserCrudService {

    @Inject
    private UserCrudAdapter userCrudAdapter;
}
