
package com.example.meetingscheduler.web;

import com.example.meetingscheduler.MeetingSchedulerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MeetingSchedulerApplication.class)
@AutoConfigureMockMvc
class SlotControllerITest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void createSlot_returnsBadRequestOnInvalidBody() throws Exception {
        mockMvc.perform(post("/api/users/{userId}/slots", "00000000-0000-0000-0000-000000000000")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
}
