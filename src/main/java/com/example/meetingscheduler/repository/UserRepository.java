
package com.example.meetingscheduler.repository;

import com.example.meetingscheduler.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
`

`java
public interface CalendarRepository extends JpaRepository<CalendarEntity, UUID> {
}
