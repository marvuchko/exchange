package pro.programista.domain.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import pro.programista.domain.entity.DomainEntity;
import pro.programista.domain.primitives.PageResult;

public interface DomainRepository<K extends Serializable, T extends DomainEntity<K>> {

  Optional<T> findById(K id);

  List<T> findByIds(List<K> ids);

  PageResult<T> findPage(long page, long size);

  T createOrUpdate(T data);

  void delete(K id);

}
