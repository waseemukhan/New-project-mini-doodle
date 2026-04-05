
package com.example.meetingscheduler.service;

import com.example.meetingscheduler.domain.*;
import com.example.meetingscheduler.repository.SlotRepository;
import com.example.meetingscheduler.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SlotServiceTest {

    @Test
    void createSlot_failsOnOverlap() {
        var slotRepo = mock(SlotRepository.class);
        var userRepo = mock(UserRepository.class);
        var service = new SlotService(slotRepo, userRepo);

        var user = new User();
        var calendar = new CalendarEntity();
        calendar.setId(UUID.randomUUID());
        user.setCalendar(calendar);

        when(userRepo.findById(any())).thenReturn(Optional.of(user));
        when(slotRepo.findOverlapping(any(), any(), any()))
                .thenReturn(List.of(new Slot()));

        assertThrows(IllegalStateException.class, () ->
                service.createSlot(UUID.randomUUID(), Instant.now(), Instant.now().plusSeconds(1800)));
    }
}
