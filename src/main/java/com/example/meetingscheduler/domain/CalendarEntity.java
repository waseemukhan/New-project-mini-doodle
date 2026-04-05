
@Entity
@Table(name = "calendars")
public class CalendarEntity {
    @Id @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Slot> slots = new ArrayList<>();

    // getters/setters
}
