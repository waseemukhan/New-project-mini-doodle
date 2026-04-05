
package com.example.meetingscheduler.web.controller;

import com.example.meetingscheduler.domain.Slot;
import com.example.meetingscheduler.service.SlotService;
import com.example.meetingscheduler.web.dto.SlotRequest;
import com.example.meetingscheduler.web.dto.SlotResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/users/{userId}/slots")
public class SlotController {

    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @PostMapping
    public SlotResponse createSlot(@PathVariable UUID userId,
                                   @Valid @RequestBody SlotRequest request) {
        Slot slot = slotService.createSlot(userId, request.getStartTime(), request.getEndTime());
        return toResponse(slot);
    }

    private SlotResponse toResponse(Slot slot) {
        SlotResponse resp = new SlotResponse();
        resp.setId(slot.getId().toString());
        resp.setStartTime(slot.getStartTime());
        resp.setEndTime(slot.getEndTime());
        resp.setStatus(slot.getStatus().name());
        return resp;
    }

    // update, delete, status, list...
}
