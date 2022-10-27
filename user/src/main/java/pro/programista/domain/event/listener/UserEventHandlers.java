package pro.programista.domain.event.listener;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pro.programista.domain.event.user.UserEvents;
import pro.programista.infrastructure.event.GenericDomainEventListener;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserEventHandlers {

  public static class UserCreatedEventHandler extends GenericDomainEventListener<UserEvents.UserCreatedEvent> {

    @Override
    public void handleEvent(UserEvents.UserCreatedEvent event) {
      log.info("User with ID: {} has been created", event.aggregateId());
    }

  }

  public static class UserUpdatedEventHandler extends GenericDomainEventListener<UserEvents.UserUpdatedEvent> {

    @Override
    public void handleEvent(UserEvents.UserUpdatedEvent event) {
      log.info("User with ID: {} has been updated", event.aggregateId());
    }

  }

  public static class UserDeletedEventHandler extends GenericDomainEventListener<UserEvents.UserDeletedEvent> {

    @Override
    public void handleEvent(UserEvents.UserDeletedEvent event) {
      log.info("User with ID: {} has been deleted.", event.aggregateId());
    }

  }

}
