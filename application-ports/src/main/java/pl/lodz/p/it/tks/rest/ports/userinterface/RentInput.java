package pl.lodz.p.it.tks.rest.ports.userinterface;

import pl.lodz.p.it.tks.rest.adapters.datamodel.RentEntity;
import pl.lodz.p.it.tks.rest.domainmodel.Rent;
import pl.lodz.p.it.tks.rest.domainmodel.users.Client;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Dependent
public class RentInput {

    @Inject
    private UserInput userInput;
    @Inject
    private CatalogInput catalogInput;

    public Rent convert(RentEntity rentEnt) {
        return new Rent(
                rentEnt.getId(),
                (Client) userInput.convert(rentEnt.getClientEntity()),
                catalogInput.convert(rentEnt.getCatalogEntity()),
                rentEnt.getRentDateTime(),
                rentEnt.getReturnDateTime()
        );
    }
}
