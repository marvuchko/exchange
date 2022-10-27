package pro.programista.infrastructure.context.cache;

import java.util.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.programista.domain.entity.User;
import pro.programista.domain.service.Cache;
import pro.programista.infrastructure.cache.NoopCache;
import pro.programista.infrastructure.constants.UserBeanConstants;

@Configuration
public class UserCacheContext {

  @Bean(UserBeanConstants.CACHE)
  public Cache<UUID, User> cache() {
    return new NoopCache<>();
  }

}
