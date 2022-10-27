package pro.programista.domain.primitives;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.util.ObjectUtils;
import pro.programista.domain.error.DefaultDomainError;
import pro.programista.domain.error.DomainError;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class Result<T> implements Serializable {

  T value;
  boolean success;
  DomainError error;

  public static <T extends Serializable> Result<T> success() {
    return new Result<>((T) null, true, DefaultDomainError.NONE);
  }

  public static <T> Result<T> success(T value) {
    return new Result<>(value, true, DefaultDomainError.NONE);
  }

  public static <T> Result<T> failure() {
    return new Result<>((T) null, false, DefaultDomainError.INTERNAL);
  }

  public static <T> Result<T> failure(DomainError error) {
    return new Result<>((T) null, false, error);
  }

  public static <T> Result<T> create(T value, DomainError error) {
    if (ObjectUtils.isEmpty(value)) {
      return failure(error);
    }
    return success(value);
  }

}
