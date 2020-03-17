package pl.lodz.p.it.tks.ports.aggregates;

import pl.lodz.p.it.tks.adapters.repositories.RentRepository;
import pl.lodz.p.it.tks.domainmodel.Rent;
import pl.lodz.p.it.tks.ports.infrastructure.RentOutput;
import pl.lodz.p.it.tks.ports.infrastructure.rentports.AddRentPort;
import pl.lodz.p.it.tks.ports.infrastructure.rentports.UpdateRentPort;
import pl.lodz.p.it.tks.ports.userinterface.RentInput;
import pl.lodz.p.it.tks.ports.userinterface.rentports.FilterRentsPort;
import pl.lodz.p.it.tks.ports.userinterface.rentports.GetRentsPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class RentAdapter implements AddRentPort, UpdateRentPort, FilterRentsPort, GetRentsPort {

    @Inject
    private RentRepository rentRepository;
    @Inject
    private RentOutput rentOutput;
    @Inject
    private RentInput rentInput;

    @Override
    public void addRent(Rent rent) {
        rentRepository.addRent(rentOutput.convert(rent));
    }

    @Override
    public void updateRent(UUID id, Rent rent) {
        rentRepository.updateRent(id, rentOutput.convert(rent));
    }

    public void removeRent(UUID id) {
        rentRepository.removeRent(id);
    }

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
