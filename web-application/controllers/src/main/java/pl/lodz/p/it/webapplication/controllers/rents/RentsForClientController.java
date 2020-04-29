package pl.lodz.p.it.webapplication.controllers.rents;

import lombok.Data;
import pl.lodz.p.it.model.RentWeb;
import pl.lodz.p.it.webapplication.controllers.ViewAccessController;
import uiports.aggregates.rentweb.RentWebCrudAdapter;
import uiports.aggregates.rentweb.RentWebFilterAdapter;
import uiports.aggregates.rentweb.RentWebGetAdapter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named
@ViewScoped
public @Data class RentsForClientController implements Serializable {

    @Inject
    private RentWebCrudAdapter rentCrudAdapter;
    @Inject
    private RentWebGetAdapter rentGetAdapter;
    @Inject
    private RentWebFilterAdapter rentFilterAdapter;
    @Inject
    private ViewAccessController viewAccessController;
    private List<RentWeb> unfinishedRents;
    private List<RentWeb> finishedRents;
    private String username;
    private String rentFilter;

    public void filterRents() {
        unfinishedRents = rentFilterAdapter.filterUnfinishedRentsForClient(username, rentFilter);
        finishedRents = rentFilterAdapter.filterFinishedRentsForClient(username, rentFilter);
    }

    public void removeRent(String rentId) {
        rentCrudAdapter.removeRent(UUID.fromString(rentId));
        loadData();
    }

    public String goBack() {
        return "back";
    }

    @PostConstruct
    public void loadData() {
        username = viewAccessController.getSelectedUsername();
        unfinishedRents = rentGetAdapter.getUnfinishedRentsForClient(viewAccessController.getSelectedUsername());
        finishedRents = rentGetAdapter.getFinishedRentsForClient(viewAccessController.getSelectedUsername());
    }
}
