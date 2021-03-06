package pl.lodz.p.it.tks.rest.ports.aggregates.rent.impl;

import pl.lodz.p.it.tks.rest.adapters.repositories.RentRepository;
import pl.lodz.p.it.tks.rest.domainmodel.Rent;
import pl.lodz.p.it.tks.rest.ports.aggregates.rent.RentRepoGetAdapter;
import pl.lodz.p.it.tks.rest.ports.userinterface.RentInput;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class RentRepoGetAdapterImpl implements RentRepoGetAdapter {

    @Inject
    private RentRepository rentRepository;
    @Inject
    private RentInput rentInput;

    @Override
    public List<Rent> getUnfinishedRents() {
        return rentRepository.getUnfinishedRents()
                .stream()
                .map(rentEntity -> rentInput.convert(rentEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Rent> getFinishedRents() {
        return rentRepository.getFinishedRents()
                .stream()
                .map(rentEntity -> rentInput.convert(rentEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Rent> getUnfinishedRentsForClient(String username) {
        return rentRepository.getUnfinishedRentsForClient(username)
                .stream()
                .map(rentEntity -> rentInput.convert(rentEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Rent> getFinishedRentsForClient(String username) {
        return rentRepository.getFinishedRentsForClient(username)
                .stream()
                .map(rentEntity -> rentInput.convert(rentEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Rent> getRentsForCatalog(int id) {
        return rentRepository.getRentsForCatalog(id)
                .stream()
                .map(rentEntity -> rentInput.convert(rentEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Rent> getUnfinishedRentsForCatalog(int id) {
        return rentRepository.getUnfinishedRentsForCatalog(id)
                .stream()
                .map(rentEntity -> rentInput.convert(rentEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Rent> getFinishedRentsForCatalog(int id) {
        return rentRepository.getFinishedRentsForCatalog(id)
                .stream()
                .map(rentEntity -> rentInput.convert(rentEntity))
                .collect(Collectors.toList());
    }
}
