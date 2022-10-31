package pro.programista.application;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.programista.application.controller.RestMvcController;
import pro.programista.domain.entity.TradePair;
import pro.programista.domain.primitives.PageResult;
import pro.programista.domain.primitives.Result;
import pro.programista.domain.service.TradePairService;

@RestController
@RequiredArgsConstructor
@RequestMapping(TradePairController.BASE_URL)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TradePairController extends RestMvcController {

  public static final String BASE_URL = "/trade-pair";

  TradePairService tradePairService;

  @GetMapping
  public ResponseEntity<Result<PageResult<TradePair>>> getTradePairs(@RequestParam long page, @RequestParam long size) {
    return resolve(tradePairService.findPage(page, size));
  }

}
