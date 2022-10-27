package pro.programista.domain.service;

import java.io.Serializable;
import java.util.List;
import pro.programista.domain.entity.DomainEntity;
import pro.programista.domain.primitives.PageResult;
import pro.programista.domain.primitives.Result;
import pro.programista.domain.repository.DomainRepository;

public interface CrudService<K extends Serializable, T extends DomainEntity<K>, R extends DomainRepository<K, T>> {

  Result<T> findById(K id);

  Result<List<T>> findByIds(List<K> ids);

  Result<PageResult<T>> findPage(long page, long size);

  Result<T> create(T data);

  Result<T> update(T data);

  Result<T> delete(K id);

}
