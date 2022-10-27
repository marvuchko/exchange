package pro.programista.application.controller;

import org.springframework.http.ResponseEntity;
import pro.programista.domain.primitives.Maybe;
import pro.programista.domain.primitives.Result;

public class RestMvcController {

  protected static final String ID_PATH = "/{id}";
  protected static final String ID_PARAM = "id";

  protected static <T> ResponseEntity<Result<T>> resolve(Result<T> result) {
    return Maybe.from(result)
        .match(RestMvcController::success, RestMvcController::failure);
  }

  private static <T> ResponseEntity<Result<T>> success(Result<T> result) {
    return ResponseEntity.ok(result);
  }

  private static <T> ResponseEntity<Result<T>> failure(Result<T> result) {
    return ResponseEntity.status(result.getError().getStatus()).body(result);
  }

}
