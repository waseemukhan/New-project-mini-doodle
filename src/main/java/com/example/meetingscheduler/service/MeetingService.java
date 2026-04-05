
@Service
public class MeetingService {

    private final SlotRepository slotRepository;
    private final MeetingRepository meetingRepository;

    public MeetingService(SlotRepository slotRepository, MeetingRepository meetingRepository) {
        this.slotRepository = slotRepository;
        this.meetingRepository = meetingRepository;
    }

    @Transactional
    public Meeting createMeetingFromSlot(UUID slotId, String title, String description,
                                         List<MeetingParticipant> participants) {
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new IllegalArgumentException("Slot not found"));

        if (slot.getStatus() != SlotStatus.FREE) {
            throw new IllegalStateException("Slot is not free");
        }

        Meeting meeting = new Meeting();
        meeting.setSlot(slot);
        meeting.setTitle(title);
        meeting.setDescription(description);
        participants.forEach(p -> p.setMeeting(meeting));
        meeting.setParticipants(participants);

        slot.setStatus(SlotStatus.BUSY);
        slot.setMeeting(meeting);

        return meetingRepository.save(meeting);
    }

    @Transactional
    public void cancelMeeting(UUID meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("Meeting not found"));
        Slot slot = meeting.getSlot();
        slot.setStatus(SlotStatus.FREE);
        slot.setMeeting(null);
        meetingRepository.delete(meeting);
    }
}
