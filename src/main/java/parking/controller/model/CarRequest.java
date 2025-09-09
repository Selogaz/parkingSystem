package parking.controller.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record CarRequest(
        UUID id,
        boolean isInside,
        LocalDateTime entryTime,
        LocalDateTime exitTime
) { }
