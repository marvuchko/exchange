package pro.programista.domain.primitives;

import java.util.Objects;
import java.util.function.Function;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import pro.programista.domain.exception.DomainException;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class Maybe<T> {

  Result<T> result;

  public static <T> Maybe<T> none() {
    return new Maybe<>((Result<T>) null);
  }

  public static <T> Maybe<T> from(Result<T> result) {
    return new Maybe<>(result);
  }

  private boolean hasValue() {
    return Objects.nonNull(result)
        && Objects.nonNull(result.getValue())
        && result.isSuccess();
  }

  public T getValue() {
    if (hasValue()) {
      return result.getValue();
    }
    throw new DomainException("The value can not be accessed because it does not exist.");
  }

  public <O> O match(Function<Result<T>, O> onSuccess, Function<Result<T>, O> onFailure) {
    if (hasValue()) {
      return onSuccess.apply(result);
    }
    return onFailure.apply(result);
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Maybe<?> maybe)) {
      return false;
    }

    if (!hasValue() && !maybe.hasValue()) {
      return true;
    }

    if (!hasValue() || !maybe.hasValue()) {
      return false;
    }

    return result.equals(maybe.result);
  }

  @Override
  public int hashCode() {
    return result != null ? result.hashCode() : 0;
  }

}
