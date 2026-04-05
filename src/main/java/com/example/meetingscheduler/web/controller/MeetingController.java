
@RestController
@RequestMapping("/api")
public class MeetingController {

    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping("/users/{userId}/slots/{slotId}/meeting")
    public MeetingResponse createMeeting(@PathVariable UUID userId,
                                         @PathVariable UUID slotId,
                                         @Valid @RequestBody MeetingRequest request) {
        var participants = request.getParticipants().stream().map(dto -> {
            var p = new MeetingParticipant();
            p.setEmail(dto.getEmail());
            p.setName(dto.getName());
            return p;
        }).toList();

        var meeting = meetingService.createMeetingFromSlot(
                slotId, request.getTitle(), request.getDescription(), participants);

        MeetingResponse resp = new MeetingResponse();
        resp.setId(meeting.getId().toString());
        resp.setSlotId(meeting.getSlot().getId().toString());
        resp.setTitle(meeting.getTitle());
        resp.setDescription(meeting.getDescription());
        resp.setParticipants(request.getParticipants());
        return resp;
    }

    @DeleteMapping("/meetings/{meetingId}")
    public void cancelMeeting(@PathVariable UUID meetingId) {
        meetingService.cancelMeeting(meetingId);
    }
}
