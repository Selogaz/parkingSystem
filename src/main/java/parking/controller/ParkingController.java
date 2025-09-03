package parking.controller;

import parking.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import parking.service.ParkingService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;

    @Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping()
    public List<Car> getEntryRequest() {
        return parkingService.getEntry();
    }

    @PostMapping("/entry")
    public Car setEntry(@RequestBody Car newRequest) {
        newRequest.setId(UUID.randomUUID());
        return parkingService.entryCar(newRequest.getId());
    }

    @PostMapping("/exit")
    public Car setExit(@RequestBody Car newRequest) {
        return parkingService.exitCar(newRequest.getId());
    }
}
