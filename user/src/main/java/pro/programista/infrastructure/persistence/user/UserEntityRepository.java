package pro.programista.infrastructure.persistence.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import pro.programista.domain.entity.User;
import pro.programista.domain.primitives.PageResult;
import pro.programista.domain.repository.UserRepository;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserEntityRepository implements UserRepository {

  UserJpaRepository userJpaRepository;

  @Override
  public Optional<User> findById(UUID id) {
    return userJpaRepository.findById(id)
        .map(UserEntityRepository::toDomain);
  }

  @Override
  public List<User> findByIds(List<UUID> ids) {
    return userJpaRepository.findAllById(ids).stream()
        .map(UserEntityRepository::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public PageResult<User> findPage(long page, long size) {
    var pageData = userJpaRepository.findAll(PageRequest.of((int) page, (int) size))
        .map(UserEntityRepository::toDomain);

    return new PageResult<>(
        pageData.getContent(),
        pageData.getTotalElements(),
        pageData.getTotalPages(),
        pageData.getNumber()
    );
  }

  @Override
  public User createOrUpdate(User data) {
    var entity = UserEntityRepository.toEntity(data);
    userJpaRepository.save(entity);
    return UserEntityRepository.toDomain(entity);
  }

  @Override
  public void delete(UUID id) {
    userJpaRepository.deleteById(id);
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
