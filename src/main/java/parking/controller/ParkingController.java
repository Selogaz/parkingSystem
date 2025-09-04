package parking.controller;

import parking.dto.Response;
import parking.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import parking.service.ParkingService;

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
    public Response entryCar(@RequestBody Car newRequest) {
        newRequest.setId(UUID.randomUUID());
        return parkingService.indexEntry(newRequest.getId());
    }

    @PostMapping("/exit")
    public Response exitCar(@RequestBody Car newRequest) {
        return parkingService.indexExit(newRequest.getId());
    }

    @PostMapping("change_time")
    public Response changeTime(@RequestBody Car newRequest) {
        return parkingService.indexChangeTime(newRequest.getId());
    }
}
