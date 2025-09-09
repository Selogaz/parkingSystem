package parking.controller.converter;

import parking.controller.model.CarRequest;
import parking.controller.model.Response;
import parking.model.Car;

public class Converter {
    public static Response toResponse(Car car) {
        Response response = new Response();
        response.setCarId(car.getId());
        return response;
    }

    public static Car fromRequestToCar(CarRequest carRequest) {
        Car car = new Car();
        car.setId(carRequest.id());
        car.setEntryTime(carRequest.entryTime());
        car.setExitTime(carRequest.exitTime());
        return car;
    }
}
