
@Entity
@Table(name = "meeting_participants")
public class MeetingParticipant {
    @Id @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = false)
    private Meeting meeting;

    @Column(nullable = false)
    private String email;

    private String name;

    // getters/setters
}
