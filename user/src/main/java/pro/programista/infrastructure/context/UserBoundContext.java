package pro.programista.infrastructure.context;

import java.io.Serializable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.programista.domain.repository.UserRepository;
import pro.programista.domain.service.UserService;
import pro.programista.domain.service.impl.UserServiceImpl;
import pro.programista.infrastructure.Cache;
import pro.programista.infrastructure.persistence.UserJpaDao;
import pro.programista.infrastructure.persistence.UserJpaRepository;

@Configuration
public class UserBoundContext {

  @Bean
  public UserRepository userRepository(Cache<Serializable, Serializable> cache, UserJpaRepository userJpaRepository) {
    return new UserJpaDao(userJpaRepository);
  }

  @Bean
  public UserService userServiceService(UserRepository userRepository) {
    return new UserServiceImpl(userRepository);
  }

}
