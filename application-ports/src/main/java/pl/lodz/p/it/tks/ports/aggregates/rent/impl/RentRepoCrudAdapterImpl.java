package pl.lodz.p.it.tks.ports.aggregates.rent.impl;

import pl.lodz.p.it.tks.adapters.repositories.RentRepository;
import pl.lodz.p.it.tks.domainmodel.Rent;
import pl.lodz.p.it.tks.ports.aggregates.rent.RentRepoCrudAdapter;
import pl.lodz.p.it.tks.ports.infrastructure.RentOutput;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

@Named
@Dependent
public class RentRepoCrudAdapterImpl implements RentRepoCrudAdapter {

    @Inject
    private RentRepository rentRepository;
    @Inject
    private RentOutput rentOutput;

    @Override
    public void addRent(Rent rent) {
        rentRepository.addRent(rentOutput.convert(rent));
    }

    @Override
    public void updateRent(UUID id, Rent rent) {
        rentRepository.updateRent(id, rentOutput.convert(rent));
    }

    @Override
    public void removeRent(UUID id) {
        rentRepository.removeRent(id);
    }
}
