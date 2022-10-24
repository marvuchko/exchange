package pro.programista.domain.repository;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pro.programista.domain.entity.BaseEntity;

public interface BaseRepository<K extends Serializable, T extends BaseEntity<K>> {

  Optional<T> findById(K id);

  Set<T> findByIds(Set<K> ids);

  Page<T> findPage(Pageable pageable);

  T createOrUpdate(T data);

  Set<T> bulkCreateOrUpdate(Set<T> data);

  void delete(K id);

  void bulkDelete(Set<K> ids);

}
