package parking.controller.converter;

import parking.controller.model.CarRequest;
import parking.controller.model.Response;
import parking.model.Car;
import parking.service.model.CarService;

public class Converter {
    public static Response toResponse(CarService car) {
        Response response = new Response();
        response.setCarId(car.getId());
        return response;
    }

    public static CarService fromRequestToCar(CarRequest carRequest) {
        CarService car = new CarService();
        car.setId(carRequest.id());
        car.setEntryTime(carRequest.entryTime());
        car.setExitTime(carRequest.exitTime());
        return car;
    }
}
