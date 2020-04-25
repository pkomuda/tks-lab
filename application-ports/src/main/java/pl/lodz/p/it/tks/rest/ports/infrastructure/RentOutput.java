package pl.lodz.p.it.tks.rest.ports.infrastructure;

import pl.lodz.p.it.tks.rest.adapters.datamodel.RentEntity;
import pl.lodz.p.it.tks.rest.adapters.datamodel.users.ClientEntity;
import pl.lodz.p.it.tks.rest.domainmodel.Rent;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Dependent
public class RentOutput {

    @Inject
    private UserOutput userOutput;
    @Inject
    private CatalogOutput catalogOutput;

    public RentEntity convert(Rent rent) {
        return new RentEntity(
                rent.getId(),
                (ClientEntity) userOutput.convert(rent.getClient()),
                catalogOutput.convert(rent.getCatalog()),
                rent.getRentDateTime(),
                rent.getReturnDateTime()
        );
    }
}
