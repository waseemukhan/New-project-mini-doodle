
@Entity
@Table(name = "meetings")
public class Meeting {
    @Id @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "slot_id", nullable = false, unique = true)
    private Slot slot;

    @Column(nullable = false)
    private String title;

    private String description;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MeetingParticipant> participants = new ArrayList<>();

    // getters/setters
}
