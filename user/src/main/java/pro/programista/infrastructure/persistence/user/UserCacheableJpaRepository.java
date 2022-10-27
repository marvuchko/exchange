package pro.programista.infrastructure.persistence.user;

import java.util.UUID;
import pro.programista.domain.entity.User;
import pro.programista.domain.repository.CacheableRepository;
import pro.programista.domain.repository.DomainRepository;
import pro.programista.domain.repository.UserRepository;
import pro.programista.domain.service.Cache;

public class UserCacheableJpaRepository
    extends CacheableRepository<UUID, User>
    implements UserRepository {

  public UserCacheableJpaRepository(
      Cache<UUID, User> cache,
      DomainRepository<UUID, User> delegate
  ) {
    super(cache, delegate);
  }
}
