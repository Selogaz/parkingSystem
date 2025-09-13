package parking.controller.converter;

import parking.controller.model.EntryRequest;
import parking.controller.model.Response;
import parking.service.model.EntryModel;

public class Converter {
    public static Response toResponse(EntryModel car) {
        Response response = new Response();
        response.setCarId(car.getId());
        return response;
    }

    public static EntryModel fromRequestToCar(EntryRequest entryRequest) {
        EntryModel car = new EntryModel();
        car.setId(entryRequest.id());
        car.setEntryTime(entryRequest.entryTime());
        car.setExitTime(entryRequest.exitTime());
        return car;
    }
}
