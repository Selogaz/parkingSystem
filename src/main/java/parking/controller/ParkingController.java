package parking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import parking.dto.Response;
import parking.exceptions.ParkingException;
import parking.exceptions.PaymentRequiredException;
import parking.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import parking.service.ParkingService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;
    private final String DEFAULT_ERROR_MESSAGE = "Неизвестная ошибка!";

    @Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping()
    public List<Car> getEntryRequest() {
        return parkingService.getEntry();
    }

    @PostMapping("/entry")
    public ResponseEntity<Response> entryCar(@RequestBody Car newRequest) {
        newRequest.setId(UUID.randomUUID());
        return ResponseEntity.ok(parkingService.indexEntry(newRequest.getId()));
    }

    @PostMapping("/exit")
    public ResponseEntity<Response> exitCar(@RequestBody Car newRequest) {
        return ResponseEntity.ok(parkingService.indexExit(newRequest.getId()));
    }

    @PostMapping("change_time")
    public ResponseEntity<Response> changeTime(@RequestBody Car newRequest) {
        return ResponseEntity.ok(parkingService.indexChangeTime(newRequest.getId()));
    }

    @ExceptionHandler(ParkingException.class)
    public ResponseEntity<Response> handleParkingException(ParkingException ex) {
        System.err.println(ex.getMessage());
        return ResponseEntity.badRequest()
                .body(new Response(ex.getMessage()));
    }

    @ExceptionHandler(PaymentRequiredException.class)
    public ResponseEntity<Response> handlePaymentRequiredException(PaymentRequiredException ex) {
        System.err.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED)
                .body(new Response(ex.getMessage()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Response> handleNotFound(NoSuchElementException ex) {
        System.err.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Response(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleInternalError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Response(DEFAULT_ERROR_MESSAGE + ex.getMessage()));
    }
}
