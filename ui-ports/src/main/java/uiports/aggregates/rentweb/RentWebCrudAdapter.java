package uiports.aggregates.rentweb;

import uiports.ports.rentweb.AddRentWebPort;
import uiports.ports.rentweb.FinishRentWebPort;
import uiports.ports.rentweb.RemoveRentWebPort;

public interface RentWebCrudAdapter extends AddRentWebPort, FinishRentWebPort, RemoveRentWebPort {

}
