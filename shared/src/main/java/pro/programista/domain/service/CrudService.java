package pro.programista.domain.service;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pro.programista.domain.entity.BaseEntity;
import pro.programista.domain.repository.BaseRepository;

public interface CrudService<K extends Serializable, T extends BaseEntity<K>, R extends BaseRepository<K, T>> {

  Optional<T> findById(K id);

  Set<T> findByIds(Set<K> ids);

  Page<T> findPage(Pageable pageable);

  T createOrUpdate(T data);

  Set<T> bulkCreateOrUpdate(Set<T> data);

  void delete(K id);

  void bulkDelete(Set<K> ids);

}
