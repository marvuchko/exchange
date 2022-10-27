package pro.programista.domain.repository;

import static java.util.Objects.nonNull;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.util.CollectionUtils;
import pro.programista.domain.entity.DomainEntity;
import pro.programista.domain.primitives.PageResult;
import pro.programista.domain.service.Cache;

public interface DomainRepository<K extends Serializable, T extends DomainEntity<K>> {

  Optional<T> findById(K id);

  List<T> findByIds(List<K> ids);

  PageResult<T> findPage(long page, long size);

  T createOrUpdate(T data);

  void delete(K id);

  @RequiredArgsConstructor
  @FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
  class CacheableRepository<
      K extends Serializable,
      T extends DomainEntity<K>> implements DomainRepository<K, T> {

    Cache<K, T> cache;
    DomainRepository<K, T> delegate;

    @Override
    public Optional<T> findById(K id) {
      T data = cache.get(id);
      if (nonNull(data)) {
        return Optional.of(data);
      }
      var entityOptional = delegate.findById(id);
      entityOptional.ifPresent(entity -> cache.put(entity.getId(), entity));
      return entityOptional;
    }

    @Override
    public List<T> findByIds(List<K> ids) {
      List<T> data = cache.get(ids);
      if (!CollectionUtils.isEmpty(data)) {
        return data;
      }
      List<T> entities = delegate.findByIds(ids);
      entities.forEach(entity -> cache.put(entity.getId(), entity));
      return entities;
    }

    @Override
    public PageResult<T> findPage(long page, long size) {
      return delegate.findPage(page, size);
    }

    @Override
    public T createOrUpdate(T data) {
      var entity = delegate.createOrUpdate(data);
      cache.put(entity.getId(), entity);
      return entity;
    }

    @Override
    public void delete(K id) {
      delegate.delete(id);
      cache.remove(id);
    }

  }
}
