package parking.controller;

import parking.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import parking.service.ParkingService;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;

    @Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping("/entry")
    public List<Car> getEntryRequest() {
        return parkingService.getEntry();
    }

    @PostMapping()
    public Car setEntry(@RequestBody Car newRequest) {
        return parkingService.entryCar(newRequest.getId(), newRequest.getCarNumber());
    }
}
