package pro.programista.infrastructure;

import static java.util.Objects.nonNull;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.util.CollectionUtils;
import pro.programista.domain.entity.BaseEntity;
import pro.programista.domain.repository.BaseRepository;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public abstract class CacheableRepository<
    K extends Serializable,
    T extends BaseEntity<K>>
    implements BaseRepository<K, T> {

  Cache<K, T> cache;
  BaseRepository<K, T> repository;

  @Override
  public Optional<T> findById(K id) {
    T data = cache.get(id);
    if (nonNull(data)) {
      return Optional.of(data);
    }
    return repository.findById(id);
  }

  @Override
  public Set<T> findByIds(Set<K> ids) {
    Set<T> data = cache.get(ids);
    if (!CollectionUtils.isEmpty(data)) {
      return data;
    }
    return repository.findByIds(ids);
  }
}
