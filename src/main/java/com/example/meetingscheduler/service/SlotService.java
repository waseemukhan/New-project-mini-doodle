
package com.example.meetingscheduler.service;

import com.example.meetingscheduler.domain.*;
import com.example.meetingscheduler.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class SlotService {

    private final SlotRepository slotRepository;
    private final UserRepository userRepository;

    public SlotService(SlotRepository slotRepository, UserRepository userRepository) {
        this.slotRepository = slotRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Slot createSlot(UUID userId, Instant start, Instant end) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        CalendarEntity calendar = user.getCalendar();
        if (calendar == null) {
            calendar = new CalendarEntity();
            calendar.setOwner(user);
            user.setCalendar(calendar);
        }

        validateNoOverlap(calendar.getId(), start, end);

        Slot slot = new Slot();
        slot.setCalendar(calendar);
        slot.setStartTime(start);
        slot.setEndTime(end);
        slot.setStatus(SlotStatus.FREE);

        return slotRepository.save(slot);
    }

    private void validateNoOverlap(UUID calendarId, Instant start, Instant end) {
        List<Slot> overlapping = slotRepository.findOverlapping(calendarId, start, end);
        if (!overlapping.isEmpty()) {
            throw new IllegalStateException("Overlapping slot");
        }
    }

    // updateSlot, deleteSlot, changeStatus, listSlots...
}
