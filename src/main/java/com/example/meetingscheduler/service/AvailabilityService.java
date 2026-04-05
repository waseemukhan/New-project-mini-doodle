
@Service
public class AvailabilityService {

    private final SlotRepository slotRepository;
    private final UserRepository userRepository;

    public AvailabilityService(SlotRepository slotRepository, UserRepository userRepository) {
        this.slotRepository = slotRepository;
        this.userRepository = userRepository;
    }

    public List<Slot> getUserSlotsInRange(UUID userId, Instant from, Instant to) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        CalendarEntity calendar = user.getCalendar();
        if (calendar == null) {
            return List.of();
        }
        return slotRepository.findByCalendarIdAndStartTimeBetween(calendar.getId(), from, to);
    }
}
