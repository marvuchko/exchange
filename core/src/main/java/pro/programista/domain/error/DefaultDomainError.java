package pro.programista.domain.error;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum DefaultDomainError implements DomainError {

  NONE(200, "No errors."),
  BAD_REQUEST(400, "Bad request."),
  INTERNAL(500, "Internal server error."),
  NOT_FOUND(404, "Not found."),

  ;

  int status;
  String message;

  @Override
  public int getStatus() {
    return status;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
