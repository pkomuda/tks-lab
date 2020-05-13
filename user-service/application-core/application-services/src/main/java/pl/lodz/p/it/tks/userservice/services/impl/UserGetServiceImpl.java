package pl.lodz.p.it.tks.userservice.services.impl;

import pl.lodz.p.it.tks.userservice.adapters.aggregates.UserGetAdapter;
import pl.lodz.p.it.tks.userservice.services.UserGetService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Dependent
public class UserGetServiceImpl implements UserGetService {

    @Inject
    private UserGetAdapter userGetAdapter;
}
