package parking.controller.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Response {
    private UUID carId;
    private String errorMsg;

    public Response(String defaultErrorMessage) {
        this.errorMsg = defaultErrorMessage;
    }

    public Response() {
    }
}
