
@Entity
@Table(name = "slots")
public class Slot {
    @Id @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "calendar_id", nullable = false)
    private CalendarEntity calendar;

    @Column(nullable = false)
    private Instant startTime;

    @Column(nullable = false)
    private Instant endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SlotStatus status = SlotStatus.FREE;

    @OneToOne(mappedBy = "slot", cascade = CascadeType.ALL)
    private Meeting meeting;

    // getters/setters
}
