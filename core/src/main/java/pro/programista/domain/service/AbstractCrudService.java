package pro.programista.domain.service;

import java.io.Serializable;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import pro.programista.domain.entity.DomainEntity;
import pro.programista.domain.error.DefaultDomainError;
import pro.programista.domain.primitives.PageResult;
import pro.programista.domain.primitives.Result;
import pro.programista.domain.repository.DomainRepository;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class AbstractCrudService<
    K extends Serializable,
    T extends DomainEntity<K>,
    R extends DomainRepository<K, T>> implements CrudService<K, T, R> {

  R repository;

  @Override
  public Result<T> findById(K id) {
    return repository.findById(id)
        .map(Result::success)
        .orElse(Result.failure());
  }

  @Override
  public Result<List<T>> findByIds(List<K> ids) {
    var data = repository.findByIds(ids);
    return Result.create(data, DefaultDomainError.NOT_FOUND);
  }

  @Override
  public Result<PageResult<T>> findPage(long page, long size) {
    var data = repository.findPage(page, size);
    return Result.create(data, DefaultDomainError.NOT_FOUND);
  }

  @Override
  public Result<T> create(T data) {
    var entity = repository.createOrUpdate(data);
    return Result.create(entity, DefaultDomainError.BAD_REQUEST);
  }

  @Override
  public Result<T> update(T data) {
    var entity = repository.createOrUpdate(data);
    return Result.create(entity, DefaultDomainError.BAD_REQUEST);
  }

  @Override
  public Result<T> delete(K id) {
    Result<T> resultById = findById(id);

    if (!resultById.isSuccess()) {
      return resultById;
    }

    var entity = resultById.getValue();
    repository.delete(entity.getId());

    return Result.create(entity, DefaultDomainError.BAD_REQUEST);
  }

}
