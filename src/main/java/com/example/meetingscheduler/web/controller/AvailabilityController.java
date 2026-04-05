
@RestController
@RequestMapping("/api")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @GetMapping("/users/{userId}/availability")
    public AvailabilityResponse getUserAvailability(@PathVariable UUID userId,
                                                    @RequestParam Instant from,
                                                    @RequestParam Instant to) {
        var slots = availabilityService.getUserSlotsInRange(userId, from, to);
        var resp = new AvailabilityResponse();
        resp.setUserId(userId.toString());
        resp.setFrom(from);
        resp.setTo(to);
        resp.setSlots(slots.stream().map(s -> {
            var v = new AvailabilityResponse.SlotView();
            v.setStartTime(s.getStartTime());
            v.setEndTime(s.getEndTime());
            v.setStatus(s.getStatus().name());
            return v;
        }).toList());
        return resp;
    }
}
