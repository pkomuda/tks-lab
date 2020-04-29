
package uiports.converters;


import pl.lodz.p.it.model.RentWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.tks.rest.domainmodel.Rent;

import java.util.List;
import java.util.stream.Collectors;

public class RentWebConverter {

    public static RentWeb domainToWeb(Rent rent) {
        return new RentWeb(
                rent.getId(),
                (ClientWeb) UserWebConverter.domainToWeb(rent.getClient()),
                CatalogWebConverter.domainToWeb(rent.getCatalog()),
                rent.getRentDateTime(),
                rent.getReturnDateTime()
        );
    }

    public static List<RentWeb> domainToWebRents(List<Rent> rents) {
        return rents
                .stream()
                .map(RentWebConverter::domainToWeb)
                .collect(Collectors.toList());
    }
}
