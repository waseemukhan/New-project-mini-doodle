
package com.example.doodle.web.dto;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record SlotRequest(
    @NotNull Instant start,
    @NotNull Instant end
) {}

public record SlotResponse(
    UUID id,
    Instant start,
    Instant end,
    String status
) {
    public static SlotResponse from(com.example.doodle.domain.Slot slot) {
        return new SlotResponse(
            slot.getId(),
            slot.getStart(),
            slot.getEnd(),
            slot.getStatus().name()
        );
    }
}
