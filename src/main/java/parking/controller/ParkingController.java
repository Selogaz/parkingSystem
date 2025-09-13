package parking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import parking.controller.converter.Converter;
import parking.controller.model.EntryRequest;
import parking.controller.model.Response;

import parking.service.model.EntryModel;
import parking.service.parking.ParkingException;
import parking.service.payment.PaymentRequiredException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import parking.service.parking.ParkingService;

import java.util.Collection;
import java.util.NoSuchElementException;

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
    public Collection<EntryModel> getEntryRequest() {
        return parkingService.getEntry();
    }

    @PostMapping("/entry")
    public ResponseEntity<Response> entryCar() {
        Response response = Converter.toResponse(parkingService.entryCar());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/exit")
    public ResponseEntity<Response> exitCar(@RequestBody EntryRequest newRequest) {
        EntryModel car = Converter.fromRequestToCar(newRequest);
        Response response = Converter.toResponse(parkingService.exitCar(car.getId()));
        return ResponseEntity.ok(response);
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
