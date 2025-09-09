package parking.controller.converter;

import parking.controller.model.Response;
import parking.model.Car;

public class Converter {
    public static Response toResponse(Car car) {
        Response response = new Response();
        response.setCarId(car.getId());
        return response;
    }
}
