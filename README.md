# Meeting Scheduler

Meeting Scheduler

Mini Doodle-like meeting scheduling backend built with Spring Boot.

Features

- Users have personal calendars (domain concept).
- Manage time slots (create, update, delete, mark FREE/BUSY).
- Convert FREE slots into meetings with title, description, participants.
- Query free/busy availability in a time range.
- Metrics via Spring Boot Actuator + Micrometer.

Tech Stack

- Java 17, Spring Boot 3
- Spring Web, Spring Data JPA, Spring Validation
- PostgreSQL
- Micrometer + Prometheus
- Docker & docker-compose

Running locally

Prerequisites

- Docker & Docker Compose
- Java 17 (for local build)

Steps

`bash
./mvnw clean package
docker-compose up --build
`

- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html
- Health: http://localhost:8080/actuator/health
- Metrics: http://localhost:8080/actuator/prometheus

API Overview

Create Slot

POST /api/users/{userId}/slots

`json
{
  "startTime": "2026-04-04T10:00:00Z",
  "endTime": "2026-04-04T10:30:00Z"
}
`

Create Meeting

POST /api/users/{userId}/slots/{slotId}/meeting

`json
{
  "title": "Project Sync",
  "description": "Weekly sync",
  "participants": [
    { "email": "jammy@example.com", "name": "Jammy" }
  ]
}
`

User Availability

GET /api/users/{userId}/availability?from=...&to=...

Metrics

- /actuator/metrics
- /actuator/prometheus

Custom:
- meetingscreatedtotal

Tests

`bash
./mvnw test
`

Design Notes

- Slots cannot overlap within a calendar.
- Meetings are always tied to a slot (BUSY).
- Availability is computed from slots in a time range.
