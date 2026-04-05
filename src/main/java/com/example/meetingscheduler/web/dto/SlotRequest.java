package com.example.meetingscheduler.web.dto;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public class SlotRequest {
    @NotNull
    private Instant startTime;
    @NotNull
    private Instant endTime;
    // getters/setters
}
