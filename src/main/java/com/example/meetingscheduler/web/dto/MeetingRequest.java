
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class MeetingRequest {
    @NotBlank
    private String title;
    private String description;
    private List<ParticipantDto> participants;

    public static class ParticipantDto {
        @NotBlank
        private String email;
        private String name;
        // getters/setters
    }
    // getters/setters
}
