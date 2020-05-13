package pl.lodz.p.it.tks.rest.ports.aggregates.rent.impl;

import pl.lodz.p.it.tks.rest.adapters.repositories.RentRepository;
import pl.lodz.p.it.tks.rest.domainmodel.Rent;
import pl.lodz.p.it.tks.rest.ports.aggregates.rent.RentRepoFilterAdapter;
import pl.lodz.p.it.tks.rest.ports.userinterface.RentInput;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class RentRepoFilterImpl implements RentRepoFilterAdapter {

    @Inject
    RentRepository rentRepository;
    @Inject
    RentInput rentInput;

    @Override
    public List<Rent> filterUnfinishedRents(String rentFilter) {
        return rentRepository.filterUnfinishedRents(rentFilter)
                .stream()
                .map(rentEntity -> rentInput.convert(rentEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Rent> filterFinishedRents(String rentFilter) {
        return rentRepository.filterFinishedRents(rentFilter)
                .stream()
                .map(rentEntity -> rentInput.convert(rentEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Rent> filterUnfinishedRentsForClient(String username, String rentFilter) {
        return rentRepository.filterUnfinishedRentsForClient(username, rentFilter)
                .stream()
                .map(rentEntity -> rentInput.convert(rentEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Rent> filterFinishedRentsForClient(String username, String rentFilter) {
        return rentRepository.filterFinishedRentsForClient(username, rentFilter)
                .stream()
                .map(rentEntity -> rentInput.convert(rentEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Rent> filterUnfinishedRentsForCatalog(int id, String rentFilter) {
        return rentRepository.filterUnfinishedRentsForCatalog(id, rentFilter)
                .stream()
                .map(rentEntity -> rentInput.convert(rentEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Rent> filterFinishedRentsForCatalog(int id, String rentFilter) {
        return rentRepository.filterFinishedRentsForCatalog(id, rentFilter)
                .stream()
                .map(rentEntity -> rentInput.convert(rentEntity))
                .collect(Collectors.toList());
    }
}
