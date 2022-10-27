package pro.programista.domain.event.user;

import java.time.Instant;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pro.programista.domain.event.DomainEvent;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserEvents {

  public static record UserCreatedEvent(
      UUID aggregateId,
      Instant creationTimestamp,
      Instant publishTimestamp
  ) implements DomainEvent<UUID> {
  }

  public static record UserUpdatedEvent(
      UUID aggregateId,
      Instant creationTimestamp,
      Instant publishTimestamp
  ) implements DomainEvent<UUID> {
  }

  public static record UserDeletedEvent(
      UUID aggregateId,
      Instant creationTimestamp,
      Instant publishTimestamp
  ) implements DomainEvent<UUID> {
  }

}
