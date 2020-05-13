package uiports.aggregates.rentweb.impl;

import pl.lodz.p.it.model.RentWeb;
import pl.lodz.p.it.tks.appservices.services.rent.RentCrudService;
import uiports.aggregates.rentweb.RentWebCrudAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

@Named
@RequestScoped
public class RentWebCrudAdapterImpl implements RentWebCrudAdapter {

    @Inject
    private RentCrudService rentCrudService;

    @Override
    public void addRent(String username, int catalogId, int year, int month, int day, int hour, int minute) {
        rentCrudService.addRent(username, catalogId, year, month, day, hour, minute);
    }

    @Override
    public void finishRent(UUID id) {
        rentCrudService.finishRent(id.toString());
    }

    @Override
    public void removeRent(UUID id) {
        rentCrudService.removeRent(id.toString());
    }
}
