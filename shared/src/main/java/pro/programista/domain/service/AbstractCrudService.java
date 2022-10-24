package pro.programista.domain.service;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pro.programista.domain.entity.BaseEntity;
import pro.programista.domain.repository.BaseRepository;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class AbstractCrudService<
    K extends Serializable,
    T extends BaseEntity<K>,
    R extends BaseRepository<K, T>>
    implements CrudService<K, T, R> {

  R repository;

  @Override
  public Optional<T> findById(K id) {
    return repository.findById(id);
  }

  @Override
  public Set<T> findByIds(Set<K> ids) {
    return repository.findByIds(ids);
  }

  @Override
  public Page<T> findPage(Pageable pageable) {
    return repository.findPage(pageable);
  }

  @Override
  public T createOrUpdate(T data) {
    return repository.createOrUpdate(data);
  }

  @Override
  public Set<T> bulkCreateOrUpdate(Set<T> data) {
    return repository.bulkCreateOrUpdate(data);
  }

  @Override
  public void delete(K id) {
    repository.delete(id);
  }

  @Override
  public void bulkDelete(Set<K> ids) {
    repository.bulkDelete(ids);
  }
}
