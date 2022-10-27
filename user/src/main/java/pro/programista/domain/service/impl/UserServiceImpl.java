package pro.programista.domain.service.impl;

import java.time.Instant;
import java.util.UUID;
import pro.programista.domain.entity.User;
import pro.programista.domain.event.DomainEventBus;
import pro.programista.domain.event.user.UserEvents;
import pro.programista.domain.primitives.Result;
import pro.programista.domain.repository.UserRepository;
import pro.programista.domain.service.EventBusAwareCrudService;
import pro.programista.domain.service.UserService;

public class UserServiceImpl extends EventBusAwareCrudService<UUID, User, UserRepository> implements UserService {

  public UserServiceImpl(UserRepository repository, DomainEventBus eventBus) {
    super(repository, eventBus);
  }

  @Override
  public Result<User> create(User data) {
    var result = super.create(data);

    if (!result.isSuccess()) {
      var user = result.getValue();
      Instant now = Instant.now();

      eventBus.publish(new UserEvents.UserCreatedEvent(user.getId(), now, now));
    }

    return result;
  }

  @Override
  public Result<User> update(User data) {
    var result = super.update(data);

    if (!result.isSuccess()) {
      var user = result.getValue();
      Instant now = Instant.now();

      eventBus.publish(new UserEvents.UserUpdatedEvent(user.getId(), now, now));
    }

    return result;
  }

  @Override
  public Result<User> delete(UUID id) {
    var result = super.delete(id);

    if (!result.isSuccess()) {
      var user = result.getValue();
      Instant now = Instant.now();

      eventBus.publish(new UserEvents.UserDeletedEvent(user.getId(), now, now));
    }

    return result;
  }

}
