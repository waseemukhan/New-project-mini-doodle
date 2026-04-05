
import com.example.meetingscheduler.domain.Slot;
import com.example.meetingscheduler.domain.SlotStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface SlotRepository extends JpaRepository<Slot, UUID> {

    List<Slot> findByCalendarIdAndStartTimeBetween(UUID calendarId, Instant from, Instant to);

    List<Slot> findByCalendarIdAndStatusAndStartTimeBetween(
            UUID calendarId, SlotStatus status, Instant from, Instant to);

    @Query("""
           select s from Slot s
           where s.calendar.id = :calendarId
             and s.startTime < :end
             and s.endTime > :start
           """)
    List<Slot> findOverlapping(UUID calendarId, Instant start, Instant end);
}
