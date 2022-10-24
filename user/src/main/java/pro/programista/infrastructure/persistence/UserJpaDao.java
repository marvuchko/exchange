package pro.programista.infrastructure.persistence;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pro.programista.domain.entity.User;
import pro.programista.domain.repository.UserRepository;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserJpaDao implements UserRepository {

  UserJpaRepository userJpaRepository;

  @Override
  public Optional<User> findById(UUID id) {
    return userJpaRepository.findById(id)
        .map(UserJpaDao::toDomain);
  }

  @Override
  public Set<User> findByIds(Set<UUID> ids) {
    return userJpaRepository.findAllById(ids).stream()
        .map(UserJpaDao::toDomain)
        .collect(Collectors.toSet());
  }

  @Override
  public Page<User> findPage(Pageable pageable) {
    return userJpaRepository.findAll(pageable)
        .map(UserJpaDao::toDomain);
  }

  @Override
  public User createOrUpdate(User data) {
    var entity = UserJpaDao.toEntity(data);
    userJpaRepository.save(entity);
    return UserJpaDao.toDomain(entity);
  }

  @Override
  public Set<User> bulkCreateOrUpdate(Set<User> data) {
    var entities = data.stream()
        .map(UserJpaDao::toEntity)
        .collect(Collectors.toSet());

    return userJpaRepository.saveAll(entities).stream()
        .map(UserJpaDao::toDomain)
        .collect(Collectors.toSet());
  }

  @Override
  public void delete(UUID id) {
    userJpaRepository.deleteById(id);
  }

  @Override
  public void bulkDelete(Set<UUID> ids) {
    userJpaRepository.deleteAllById(ids);
  }

  private static User toDomain(UserJpaEntity entity) {
    var user = new User();
    user.setEmail(entity.getEmail());
    user.setLastName(entity.getLastName());
    user.setFirstName(entity.getFirstName());
    user.setId(entity.getId());
    return user;
  }

  private static UserJpaEntity toEntity(User user) {
    var entity = new UserJpaEntity();
    entity.setEmail(user.getEmail());
    entity.setFirstName(user.getFirstName());
    entity.setLastName(user.getLastName());
    entity.setId(user.getId());
    return entity;
  }
}
