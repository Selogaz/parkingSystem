package parking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import parking.controller.converter.Converter;
import parking.controller.model.CarRequest;
import parking.controller.model.Response;
import parking.model.Car;
import parking.service.parking.ParkingService;

@RestController
@RequestMapping("/parking")
public class TestParkingController {

    private final ParkingService parkingService;

    public TestParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PostMapping("change_time")
    public ResponseEntity<Response> changeTime(@RequestBody CarRequest newRequest) {
        Car car = Converter.fromRequestToCar(newRequest);
        Response response = Converter.toResponse(parkingService.changeEntryTime(car.getId()));
        return ResponseEntity.ok(response);
    }
}
