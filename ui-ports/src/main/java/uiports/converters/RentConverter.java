package uiports.converters;

import pl.lodz.p.it.model.RentWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.tks.domainmodel.Rent;

import java.util.List;
import java.util.stream.Collectors;

public class RentConverter {

    public static RentWeb domainToWeb(Rent rent) {
        return new RentWeb(
                rent.getId(),
                (ClientWeb) UserConverter.domainToWeb(rent.getClient()),
                CatalogConverter.domainToWeb(rent.getCatalog()),
                rent.getRentDateTime(),
                rent.getReturnDateTime()
        );
    }

    public static List<RentWeb> domainToWebRents(List<Rent> rents) {
        return rents
                .stream()
                .map(RentConverter::domainToWeb)
                .collect(Collectors.toList());
    }
}
