
import java.time.Instant;
import java.util.List;

public class AvailabilityResponse {
    public static class SlotView {
        private Instant startTime;
        private Instant endTime;
        private String status;
        // getters/setters
    }

    private String userId;
    private Instant from;
    private Instant to;
    private List<SlotView> slots;
    // getters/setters
}
