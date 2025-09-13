package parking.controller.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record EntryRequest(
        UUID id,
        boolean isInside,
        LocalDateTime entryTime,
        LocalDateTime exitTime
) { }
